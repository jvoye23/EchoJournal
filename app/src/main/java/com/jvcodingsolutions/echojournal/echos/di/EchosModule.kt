package com.jvcodingsolutions.echojournal.echos.di

import com.jvcodingsolutions.echojournal.echos.data.audio.AndroidAudioPlayer
import com.jvcodingsolutions.echojournal.echos.data.echo.RoomEchoDataSource
import com.jvcodingsolutions.echojournal.echos.data.recording.AndroidVoiceRecorder
import com.jvcodingsolutions.echojournal.echos.data.recording.InternalRecordingStorage
import com.jvcodingsolutions.echojournal.echos.data.settings.DataStoreSettings
import com.jvcodingsolutions.echojournal.echos.domain.audio.AudioPlayer
import com.jvcodingsolutions.echojournal.echos.domain.echo.EchoDataSource
import com.jvcodingsolutions.echojournal.echos.domain.recording.RecordingStorage
import com.jvcodingsolutions.echojournal.echos.domain.recording.VoiceRecorder
import com.jvcodingsolutions.echojournal.echos.domain.settings.SettingsPreferences
import com.jvcodingsolutions.echojournal.echos.presentation.create_echo.CreateEchoViewModel
import com.jvcodingsolutions.echojournal.echos.presentation.echos.EchosViewModel
import com.jvcodingsolutions.echojournal.echos.presentation.settings.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    singleOf(::AndroidVoiceRecorder) bind VoiceRecorder::class
    singleOf(::InternalRecordingStorage) bind RecordingStorage::class
    singleOf(::AndroidAudioPlayer) bind AudioPlayer::class
    singleOf(::RoomEchoDataSource) bind EchoDataSource::class
    singleOf(::DataStoreSettings) bind SettingsPreferences::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
    viewModelOf(::SettingsViewModel)
}