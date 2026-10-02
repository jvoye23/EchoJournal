package com.jvcodingsolutions.echojournal.echos.presentation.util

import com.jvcodingsolutions.echojournal.echos.domain.echo.Echo
import com.jvcodingsolutions.echojournal.echos.presentation.echos.models.PlaybackState
import com.jvcodingsolutions.echojournal.echos.presentation.models.EchoUi
import com.jvcodingsolutions.echojournal.echos.presentation.models.MoodUi
import kotlin.time.Duration

fun Echo.toEchoUi(
    currentPlaybackDuration: Duration = Duration.ZERO,
    playbackState: PlaybackState = PlaybackState.STOPPED
): EchoUi {
    return EchoUi(
        id = id!!,
        title = title,
        mood = MoodUi.valueOf(mood.name),
        recordedAt = recordedAt,
        note = note,
        topics = topics,
        amplitudes = audioAmplitudes,
        playbackTotalDuration = audioPlaybackLength,
        audioFilePath = audioFilePath,
        playbackCurrentDuration = currentPlaybackDuration,
        playbackState = playbackState
    )
}