package com.jv23.echojournal.presentation.screens.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.echojournal.core.FileManager
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.entity.JournalEntry
import com.jv23.echojournal.domain.repository.JournalEntryRepository
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

    private val _entries = journalEntryRepository.getJournalEntries()

    // File Dir
    private var newEntryId: String? = null
    private var newEntryUri: String? = null

    // Cache Dir
    private var audioFile: File? = null

    private val _currentEntryPlaying = MutableStateFlow<JournalEntry?>(null)
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
            is EntriesAction.OnRecord -> {
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
                } else return
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
                            //Error Event
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
        }

    }

}