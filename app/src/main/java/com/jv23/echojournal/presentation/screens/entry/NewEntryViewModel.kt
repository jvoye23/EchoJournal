package com.jv23.echojournal.presentation.screens.entry

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import androidx.room.util.query
import com.jv23.echojournal.core.FileManager
import com.jv23.echojournal.di.AppContainer
import com.jv23.echojournal.di.AppDataContainer
import com.jv23.echojournal.di.MyApplication
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.entity.JournalEntryEntity
import com.jv23.echojournal.domain.repository.JournalEntryRepository
import com.jv23.echojournal.navigation.HomeRoute
import com.jv23.echojournal.navigation.NewEntryRoute

import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryAction
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryEvent
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration


class NewEntryViewModel(
    private val journalEntryRepository: JournalEntryRepository,
    private val audioPlayer: AudioPlayer,
    private val fileManager: FileManager,
    val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY]) as MyApplication
                val journalEntryRepository = application.journalEntryRepository
                val fileManager = application.fileManager
                val audioPlayer = application.audioPlayer
                val savedStateHandle = extras.createSavedStateHandle()

                return NewEntryViewModel(
                    journalEntryRepository = journalEntryRepository,
                    savedStateHandle = savedStateHandle,
                    fileManager = fileManager,
                    audioPlayer = audioPlayer
                ) as T
            }
        }
    }

    private val args = savedStateHandle.toRoute<NewEntryRoute>()
    private val fileUri = args.fileUri
    private val id = args.id

    private var isLoaded = false

    private val _state = MutableStateFlow(NewEntryState())
    val state = _state
        .onStart {
            if (!isLoaded) {
                loadRecordingFile()
            }
            initObservers()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            NewEntryState()
        )

    private val _eventChannel = Channel<NewEntryEvent>()
    val events = _eventChannel.receiveAsFlow()

    private fun loadRecordingFile() {
        val recordingFile = fileManager.getFileFromUri(fileUri)
        audioPlayer.play(
            file = recordingFile,
            onComplete = {
                _state.update { it.copy(isPlaying = false) }
            },
            shouldPlayImmediately = false
        )
        val durationOfAudio = fileManager.getDurationOfAudioFile(recordingFile)
        _state.update { it.copy(maxPlaybackInSeconds = durationOfAudio) }
        isLoaded = true
    }

    private fun initObservers() {
        combine(
            _state,
            journalEntryRepository.getAllTopics()
        ) { curState, topics ->
            val query = curState.inputTopic
            val unselectedTopics = if (query.isNotBlank()) {
                topics.filter { it.contains(query, true) }
            } else {
                if (curState.isTopicFieldFocused) {
                    topics.filter { !curState.selectedTopics.contains(it) }.take(3)
                } else emptyList()
            }
            val isNewTopic = query.isNotBlank() && unselectedTopics.none { it.equals(query, true) }
            _state.update {
                it.copy(
                    unselectedTopics = unselectedTopics.toSet(),
                    isNewTopic = isNewTopic
                )
            }
        }.launchIn(viewModelScope)

        combine(
            _state,
            audioPlayer.curPlaybackInSeconds
        ) { curState, curPlaybackInSeconds ->
            if (curState.isPlaying) {
                _state.update {
                    it.copy(
                        curPlaybackInSeconds = curPlaybackInSeconds
                    )
                }
            }
        }.launchIn(viewModelScope)

        _state.onEach { curState ->
            val canSave = curState.title.isNotBlank() && curState.selectedMood != null
            _state.update { it.copy(canSave = canSave) }
        }.launchIn(viewModelScope)



    }

    fun onAction(action: NewEntryAction) {
        when(action) {
            is NewEntryAction.OnSaveClick -> {
                audioPlayer.stopAndResetPlayer()
                viewModelScope.launch {
                    try {
                        val state = _state.value
                        val title = state.title
                        val description = state.description
                        val selectedMood = state.selectedMood!!
                        val maxPlaybackInSeconds = state.maxPlaybackInSeconds

                        val newJournalEntry = JournalEntryEntity(
                            id = id,
                            mood = selectedMood,
                            title = title,
                            description = description,
                            recordingUri = fileUri,
                            maxPlaybackInSeconds = maxPlaybackInSeconds,
                            dateTimeCreated = LocalDateTime.now(),
                            topics = state.selectedTopics
                        )
                        journalEntryRepository.upsertJournalEntry(newJournalEntry)
                        _eventChannel.send(NewEntryEvent.Success)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _eventChannel.send(NewEntryEvent.Error("Something went wrong"))
                    }
                }

            }

            NewEntryAction.OnCancelNewEntry -> {
                viewModelScope.launch {
                    // Delete the created file to not consume space.
                    fileManager.getFileFromUri(fileUri).delete()

                    _eventChannel.send(NewEntryEvent.NavigateBack)
                }
            }


            NewEntryAction.OnCreateNewTopic -> {
                viewModelScope.launch {
                    val newTopic = _state.value.inputTopic.trim().replaceFirstChar { it.uppercase() }
                    journalEntryRepository.upsertTopic(newTopic)

                    _state.update {
                        it.copy(
                            selectedTopics = it.selectedTopics + newTopic,
                            inputTopic = ""
                        )
                    }
                }

            }
            is NewEntryAction.OnDeleteTopic -> {
                _state.update { it.copy(selectedTopics = it.selectedTopics - action.value) }
            }


            is NewEntryAction.OnDescriptionChange -> {
                _state.update { it.copy(description = action.value) }
            }


            is NewEntryAction.OnInputTopic -> {
                _state.update { it.copy(inputTopic = action.value ) }

            }
            is NewEntryAction.OnSeekCurrentPlayback -> {
                val millis = action.seconds.toDuration(DurationUnit.SECONDS).toInt(DurationUnit.MILLISECONDS)
                audioPlayer.seekTo(millis)
            }


            NewEntryAction.OnSelectAudioPlayer -> TODO()


            is NewEntryAction.OnSelectMoodBottomSheet -> TODO()


            is NewEntryAction.OnSelectMood -> {
                _state.update { it.copy(selectedMood = action.mood) }
            }

            is NewEntryAction.OnSelectTopic -> {
                _state.update {
                    it.copy(
                        selectedTopics = it.selectedTopics + action.value,
                        inputTopic = ""
                    )
                }
            }
            is NewEntryAction.OnTitleChange -> {
                _state.update { it.copy(title = action.value) }
            }


            is NewEntryAction.OnToggleCancelDialog -> {

            }
            is NewEntryAction.OnTopicFieldFocusChange -> {

            }


            is NewEntryAction.OnToggleSelectMoodBottomSheet -> {
                _state.update { it.copy(isSelectMoodBottomSheetOpened = action.isOpen) }
            }

            NewEntryAction.OnAddNewTopic -> {
                viewModelScope.launch {
                    val newTopic = _state.value.inputTopic.trim().replaceFirstChar { it.uppercase() }
                    journalEntryRepository.upsertTopic(newTopic)

                    _state.update {
                        it.copy(
                            selectedTopics = it.selectedTopics + newTopic,
                            inputTopic = ""
                        )
                    }
                }

            }
        }
    }

}
