package com.jv23.echojournal.presentation.screens.home

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.jv23.echojournal.domain.audiorecorder.playback.AndroidAudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AndroidAudioRecorder
import com.jv23.echojournal.presentation.screens.home.handling.EntriesAction
import com.jv23.echojournal.presentation.screens.home.handling.EntriesState
import java.io.File

class EntriesViewModel(application: Application): AndroidViewModel(application) {

    private val appContext: Context = application.applicationContext

    var state by mutableStateOf(EntriesState())
        private set

    private val recorder by lazy {

        AndroidAudioRecorder(appContext)
   }

   private val player by lazy {
       AndroidAudioPlayer(appContext)
   }

   private var audioFile: File? = null

    fun onAction(action: EntriesAction) {
        when(action) {
            is EntriesAction.OnRecord -> {

                val cacheDir = appContext.cacheDir

                File(cacheDir, "JournalEntry.mp3").also {
                    recorder.start(it)
                    audioFile = it
                }
            }

            EntriesAction.OnCancelClick -> TODO()
            EntriesAction.OnPauseClick -> TODO()
            EntriesAction.OnResumeClick -> TODO()
            EntriesAction.OnSaveClick -> {
                recorder.stop()
            }

            EntriesAction.OnPlayClick -> {
                player.playFile(audioFile ?: return)
            }

            EntriesAction.OnSelectMoodIcon -> TODO()
        }

    }

}