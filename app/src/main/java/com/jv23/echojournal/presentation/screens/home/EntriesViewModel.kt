package com.jv23.echojournal.presentation.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jv23.echojournal.core.FileManager
//import com.jv23.echojournal.di.MyApplication
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.domain.entity.JournalEntryEntity

import com.jv23.echojournal.domain.repository.JournalEntryRepository
import com.jv23.echojournal.presentation.screens.entry.NewEntryViewModel
import com.jv23.echojournal.presentation.screens.home.handling.EntriesAction
import com.jv23.echojournal.presentation.screens.home.handling.EntriesEvent
import com.jv23.echojournal.presentation.screens.home.handling.EntriesState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class EntriesViewModel(
    val context: Context,
    private val audioPlayer: AudioPlayer,
    private val audioRecorder: AudioRecorder,
    private val fileManager: FileManager,
    private val journalEntryRepository: JournalEntryRepository
): ViewModel() {

    private val appContext = context
    private val _eventChannel = Channel<EntriesEvent>()
    val events = _eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(EntriesState())
    val state = _state
        .onStart {
            init()
        }.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            EntriesState()
        )

   /* private val recorder by lazy {

        AndroidAudioRecorder(appContext)
    }

    private val player by lazy {
       AndroidAudioPlayer(appContext)
    }*/

    private val _entries = journalEntryRepository.getAllJournalEntries()
    private val _currentEntryPlaying = MutableStateFlow<JournalEntryEntity?>(null)
    private val _isPlaying = MutableStateFlow(false)

    fun isEntriesListEmpty(): Boolean {
        return true
    }

    // File Dir
    private var newEntryId: String? = null
    private var newEntryUri: String? = null

    // Cache Dir
    private var audioFile: File? = null



    fun testingDI(){
        viewModelScope.launch {
            journalEntryRepository.testDi("test@di.com", "test123")
        }
    }

    private fun init() {
        combine(
            _entries,
            _state
        ) { entries, curState ->
            _state.update { stateToUpdate ->
                stateToUpdate.copy(
                    dateToJournalsMap = entries
                        .groupBy { it.dateTimeCreated.toLocalDate() }
                        .toSortedMap(compareByDescending { it })
                )

            }

        }.launchIn(viewModelScope)

        audioRecorder.durationInMillis.onEach { duratinInMillis ->
            _state.update { it.copy(
                durationInSeconds = duratinInMillis.toDuration(DurationUnit.MILLISECONDS).inWholeSeconds
            ) }
        }.launchIn(viewModelScope)
    }

    private fun resetNewEntries(){
        newEntryId = null
        newEntryUri = null
    }

    private fun onRefresh(){
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }

            val entries = journalEntryRepository.getAllJournalEntries()
            _state.update { it.copy(
                isLoading = false


            ) }
            combine(
                _entries,
                _state
            ) { entries, curState ->
                _state.update { stateToUpdate ->
                    stateToUpdate.copy(
                        dateToJournalsMap = entries
                            .groupBy { it.dateTimeCreated.toLocalDate() }
                            .toSortedMap(compareByDescending { it })
                    )

                }

            }
        }
    }

    fun onAction(action: EntriesAction) {
        when(action) {
            is EntriesAction.OnToggleRecord -> onToggleRecord()
            EntriesAction.OnCancelClick -> onCancelClick()
            EntriesAction.OnPauseClick -> onPauseClick()
            EntriesAction.OnResumeClick -> onResumeClick()
            EntriesAction.OnFinishRecordingClick -> onFinishRecordingClick()
            EntriesAction.OnSelectMoodIcon -> onSelectMoodClick()
            EntriesAction.OnTestDiClick -> {testingDI()}
            is EntriesAction.OnToggleAudioRecorderBottomSheet -> onToggleAudioRecorderBottomSheet(action.isOpen)
            is EntriesAction.OnSeekCurrentPlayback -> onSeekCurrentPlayback()
            is EntriesAction.OnTogglePlayback -> onTogglePlayback(action.journalEntry)
        }
    }

    private fun onToggleRecord(){
        val hasStartedRecording = _state.value.hasStartedRecording

        if (!hasStartedRecording) {
            _state.update {
                it.copy(
                    hasStartedRecording = true,
                    isRecording = true
                )
            }
            newEntryId = UUID.randomUUID().toString()
            audioRecorder.start(newEntryId.orEmpty())
        } else {
            val shouldRecord = !_state.value.isRecording
            _state.update { it.copy(isRecording = shouldRecord) }
            if (shouldRecord) {
                audioRecorder.resume()
            } else {
                audioRecorder.pause()
            }
        }
    }
    private fun onPauseClick(){/* */}
    private fun onResumeClick(){/* */}
    private fun onSelectMoodClick(){/* */}
    private fun onTogglePlayback(entry: JournalEntryEntity){

        if (_currentEntryPlaying.value == null ||
            _currentEntryPlaying.value?.id.orEmpty() != entry.id
        ) {
            _currentEntryPlaying.update { entry }
            _isPlaying.update { true }
            audioPlayer.play(
                file = fileManager.getFileFromUri(entry.recordingUri),
                onComplete = {
                    _isPlaying.update { false }
                }
            )
        } else {
            val shouldPlay = !_isPlaying.value
            _isPlaying.update { shouldPlay }
            if (shouldPlay) {
                audioPlayer.resume()
            } else {
                audioPlayer.pause()
            }
        }

    }
    private fun onToggleAudioRecorderBottomSheet(isOpen: Boolean){
        _state.update { it.copy(isAudioRecorderBottomSheetOpened = isOpen) }
    }
    private fun onCancelClick(){
        audioRecorder.stop(discardFile = true)

        // Reset the state, close the bottom sheet
        _state.update {
            it.copy(
                isAudioRecorderBottomSheetOpened = false,
                hasStartedRecording = false,
                isRecording = false
            )
        }
        // Set the variables to null
        resetNewEntries()
    }
    private fun onFinishRecordingClick(){
        viewModelScope.launch {
            // Get the uri of the created recording file.
            newEntryUri = audioRecorder.stop().also {
                if (it.isBlank()) {
                    _eventChannel.send(EntriesEvent.NewEntryError("Error occured when creating the audio file"))
                    return@launch
                }
            }// Reset the state, close the bottom sheet
            _state.update {
                it.copy(
                    isAudioRecorderBottomSheetOpened = false,
                    hasStartedRecording = false,
                    isRecording = false
                )
            }
            try {
                _eventChannel.send(
                    EntriesEvent.NewEntrySuccess(
                        id = newEntryId!!,
                        fileUri = newEntryUri!!
                    )
                )
                resetNewEntries()
            } catch (e: Exception) {
                _eventChannel.send(EntriesEvent.NewEntryError("Something went wrong"))
            }
        }

    }
    private fun onSeekCurrentPlayback(){/* */}





}