package com.jvcodingsolutions.echojournal.echos.di

import com.jvcodingsolutions.echojournal.echos.data.recording.AndroidVoiceRecorder
import com.jvcodingsolutions.echojournal.echos.domain.recording.VoiceRecorder
import com.jvcodingsolutions.echojournal.echos.presentation.echos.EchosViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    single {
        AndroidVoiceRecorder(
            context = androidApplication(),
            applicationScope = get()
        )
    } bind VoiceRecorder::class

    viewModelOf(::EchosViewModel)
}