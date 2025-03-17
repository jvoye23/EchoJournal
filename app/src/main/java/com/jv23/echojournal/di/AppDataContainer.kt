package com.jv23.echojournal.di


import android.content.Context

import androidx.lifecycle.ViewModelProvider
import com.jv23.echojournal.core.AndroidFileManager
import com.jv23.echojournal.core.FileManager
import com.jv23.echojournal.data.database.JournalEntryDatabase
import com.jv23.echojournal.data.repository.JournalEntryRepositoryImpl
import com.jv23.echojournal.domain.audiorecorder.playback.AndroidAudioPlayer
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AndroidAudioRecorder
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.repository.JournalEntryRepository
import com.jv23.echojournal.presentation.screens.entry.NewEntryViewModel
import com.jv23.echojournal.presentation.screens.home.EntriesViewModel

class AppDataContainer(
    private val appContext: Context,
) : AppContainer {

    override val journalEntryRepository:
            JournalEntryRepository by lazy {
        JournalEntryRepositoryImpl(
            JournalEntryDatabase
                .getDatabase(appContext).getJournalEntryDao()
        )

    }
    override val audioPlayer: AudioPlayer
        get() = AndroidAudioPlayer(appContext)

    override val audioRecorder: AudioRecorder
        get() = AndroidAudioRecorder(appContext)

    override val fileManager: FileManager
        get() = AndroidFileManager(appContext)

    override val newEntryViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory { savedStateHandle ->
            NewEntryViewModel(
                journalEntryRepository = journalEntryRepository,
                audioPlayer = audioPlayer,
                fileManager = fileManager,
                savedStateHandle = savedStateHandle
            )
        }

    override val entriesViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory {
            EntriesViewModel(
                context = appContext,
                journalEntryRepository = journalEntryRepository,
                audioPlayer = audioPlayer,
                audioRecorder = audioRecorder,
                fileManager = fileManager

            )
        }
}