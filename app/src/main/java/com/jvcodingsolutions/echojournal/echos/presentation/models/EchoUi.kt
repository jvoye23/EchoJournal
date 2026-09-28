package com.jvcodingsolutions.echojournal.echos.presentation.models

import com.jvcodingsolutions.echojournal.echos.presentation.echos.models.PlaybackState
import com.jvcodingsolutions.echojournal.echos.presentation.util.toReadableTime
import kotlin.time.Duration
import kotlin.time.Instant

data class EchoUi(
    val id: Int,
    val title: String,
    val mood: MoodUi,
    val recordedAt: Instant,
    val note: String?,
    val topics: List<String>,
    val amplitudes: List<Float>,
    val playbackTotalDuration: Duration,
    val playbackCurrentDuration: Duration = Duration.ZERO,
    val playbackState: PlaybackState = PlaybackState.STOPPED,
) {
    val formattedRecordedAt = recordedAt.toReadableTime()
    val playbackRatio = (playbackCurrentDuration / playbackTotalDuration).toFloat()
}
