package com.jvcodingsolutions.echojournal.echos.domain.echo

import kotlin.time.Duration
import kotlin.time.Instant

data class Echo(
    val mood: Mood,
    val title: String,
    val note: String?,
    val topics: List<String>,
    val audioFilePath: String,
    val audioPlaybackLength: Duration,
    val audioAmplitudes: List<Float>,
    val recordedAt: Instant,
    val id: Int? = null,
)