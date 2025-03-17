package com.jv23.echojournal.di

import androidx.lifecycle.ViewModelProvider
import com.jv23.echojournal.core.FileManager
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.repository.JournalEntryRepository

interface AppContainer {
    val journalEntryRepository: JournalEntryRepository
    val audioPlayer: AudioPlayer
    val audioRecorder: AudioRecorder
    val fileManager: FileManager
    val entriesViewModelFactory: ViewModelProvider.Factory
    val newEntryViewModelFactory: ViewModelProvider.Factory
}