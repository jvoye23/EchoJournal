package com.jv23.echojournal.domain.entity

import com.jv23.echojournal.domain.data_source.model.MoodType
import java.time.Instant

data class JournalEntry(
    val id: Long,
    val title: String,
    val moodType: MoodType,
    val audioFilePath: String,
    val audioDuration: Int,
    val amplitudeLogFilePath: String,
    val description: String = "",
    val topics: List<String> = listOf(),
    val created: Instant = Instant.now()
)
