package com.jv23.echojournal.presentation.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.jv23.echojournal.core.FileManager
import com.jv23.echojournal.di.MyApplication
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.entity.JournalEntryEntity

import com.jv23.echojournal.domain.repository.JournalEntryRepository
import com.jv23.echojournal.presentation.screens.entry.NewEntryViewModel
import com.jv23.echojournal.presentation.screens.home.handling.EntriesAction
import com.jv23.echojournal.presentation.screens.home.handling.EntriesEvent
import com.jv23.echojournal.presentation.screens.home.handling.EntriesState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class EntriesViewModel(
    val context: Context,
    private val audioPlayer: AudioPlayer,
    private val audioRecorder: AudioRecorder,
    private val fileManager: FileManager,
    private val journalEntryRepository: JournalEntryRepository
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
                val audioRecorder = application.audioRecorder
                val context = application.context

                return EntriesViewModel(
                    journalEntryRepository = journalEntryRepository,

                    fileManager = fileManager,
                    audioPlayer = audioPlayer,
                    context = context,
                    audioRecorder = audioRecorder
                ) as T
            }

        }
    }



    private val appContext = context

    /*var state by mutableStateOf(EntriesState())
        private set*/
    private val _eventChannel = Channel<EntriesEvent>()
    val events = _eventChannel.receiveAsFlow()

    private val _state = MutableStateFlow(EntriesState())
    val state = _state

   /* private val recorder by lazy {

        AndroidAudioRecorder(appContext)
    }

    private val player by lazy {
       AndroidAudioPlayer(appContext)
    }*/

    private val _entries = journalEntryRepository.getAllJournalEntries()

    // File Dir
    private var newEntryId: String? = null
    private var newEntryUri: String? = null

    // Cache Dir
    private var audioFile: File? = null

    private val _currentEntryPlaying = MutableStateFlow<JournalEntryEntity?>(null)
    private val _isPlaying = MutableStateFlow(false)

    fun testingDI(){
        viewModelScope.launch {
            journalEntryRepository.testDi("test@di.com", "test123")
        }
    }

    private fun resetNewEntries(){
        newEntryId = null
        newEntryUri = null
    }

    fun onAction(action: EntriesAction) {
        when(action) {
            is EntriesAction.OnToggleRecord -> {
                /*if (!_state.value.canRecord){
                    return
                }*/

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

            EntriesAction.OnCancelClick -> {
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
            EntriesAction.OnPauseClick -> TODO()
            EntriesAction.OnResumeClick -> TODO()
            EntriesAction.OnFinishRecordingClick -> {
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

            EntriesAction.OnPlayClick -> {
                //player.playFile(audioFile ?: return)
            }

            EntriesAction.OnSelectMoodIcon -> TODO()
            EntriesAction.OnTestDiClick -> {testingDI()}
            EntriesAction.OnToggleRecording -> TODO()


            EntriesAction.OnToggleAudioRecorderBottomSheet -> {
                if (state.value.isAudioRecorderBottomSheetOpened){
                    state.update {
                        it.copy(
                            isAudioRecorderBottomSheetOpened = false
                        )
                    }
                } else {
                    state.update {
                        it.copy(
                            isAudioRecorderBottomSheetOpened = true
                        )
                    }
                }
            }
        }

    }

}