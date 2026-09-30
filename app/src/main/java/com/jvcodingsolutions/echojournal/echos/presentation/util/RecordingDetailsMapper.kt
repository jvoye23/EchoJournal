package com.jvcodingsolutions.echojournal.echos.presentation.util

import com.jvcodingsolutions.echojournal.app.navigation.NavigationRoute
import com.jvcodingsolutions.echojournal.echos.domain.recording.RecordingDetails
import kotlin.time.Duration.Companion.milliseconds

fun RecordingDetails.toCreateEchoRoute(): NavigationRoute.CreateEchoNavKey {
    return NavigationRoute.CreateEchoNavKey(
        recordingPath = this.filePath ?: throw IllegalArgumentException(
            "Recording path can't be null."
        ),
        duration = this.duration.inWholeMilliseconds,
        amplitudes = this.amplitudes.joinToString(";")
    )
}

fun NavigationRoute.CreateEchoNavKey.toRecordingDetails(): RecordingDetails {
    return RecordingDetails(
        duration = this.duration.milliseconds,
        filePath = this.recordingPath,
        amplitudes = this.amplitudes.split(";").map { it.toFloat() }
    )
}