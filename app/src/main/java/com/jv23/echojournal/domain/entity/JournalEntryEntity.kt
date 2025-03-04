package com.jv23.echojournal.domain.entity

import com.jv23.echojournal.domain.data_source.model.Mood
import java.time.LocalDateTime
import java.util.UUID

data class JournalEntryEntity(
    val id: String = UUID.randomUUID().toString(),
    val mood: Mood,
    val title: String,
    val description: String,
    val recordingUri: String,
    val maxPlaybackInSeconds: Long,
    val dateTimeCreated: LocalDateTime,
    val topics: Set<String>
)
