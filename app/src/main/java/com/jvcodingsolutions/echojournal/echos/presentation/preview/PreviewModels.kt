package com.jvcodingsolutions.echojournal.echos.presentation.preview

import com.jvcodingsolutions.echojournal.echos.presentation.echos.models.PlaybackState
import com.jvcodingsolutions.echojournal.echos.presentation.models.EchoUi
import com.jvcodingsolutions.echojournal.echos.presentation.models.MoodUi
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.Duration.Companion.seconds
import kotlin.time.Instant

data object PreviewModels {

    val echoUi = EchoUi(
        id = 0,
        title = "My audio memo",
        mood = MoodUi.STRESSED,
        recordedAt = Clock.System.now(),
        note = (1..50).joinToString(" ") { "Hello" },
        topics = listOf("Love", "Work"),
        amplitudes = (1..30).map { Random.nextFloat() },
        playbackTotalDuration = 250.seconds,
        playbackCurrentDuration = 120.seconds,
        playbackState = PlaybackState.PAUSED
    )
}