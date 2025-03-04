package com.jv23.echojournal.di

import android.app.Application
import android.content.Context
import com.jv23.echojournal.core.AndroidFileManager
import com.jv23.echojournal.core.FileManager
import com.jv23.echojournal.data.database.JournalEntryDatabase
import com.jv23.echojournal.data.repository.JournalEntryRepositoryImpl
import com.jv23.echojournal.domain.audiorecorder.playback.AndroidAudioPlayer
import com.jv23.echojournal.domain.audiorecorder.playback.AudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AndroidAudioRecorder
import com.jv23.echojournal.domain.audiorecorder.record.AudioRecorder
import com.jv23.echojournal.domain.repository.JournalEntryRepository

class MyApplication(

): Application() {

    val context: Context
        get() = applicationContext

    val journalEntryRepository: JournalEntryRepository by lazy {
        JournalEntryRepositoryImpl(
            JournalEntryDatabase
                .getDatabase(this.applicationContext).getJournalEntryDao()
        )
    }

    val fileManager: FileManager
        get() = AndroidFileManager(this.applicationContext)

    val audioPlayer: AudioPlayer
        get() = AndroidAudioPlayer(this.applicationContext)

    val audioRecorder: AudioRecorder
        get() = AndroidAudioRecorder(this.applicationContext)

}