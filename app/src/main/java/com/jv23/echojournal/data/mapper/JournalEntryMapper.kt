package com.jv23.echojournal.data.mapper


import com.jv23.echojournal.data.entity.JournalEntryEntityDb
import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.domain.entity.JournalEntryEntity
import com.jv23.echojournal.presentation.core.utils.toLocalDateTime
import com.jv23.echojournal.presentation.core.utils.toMillis

fun JournalEntryEntity.toJournalEntryEntityDb(): JournalEntryEntityDb = JournalEntryEntityDb(
    id = id,
    mood = mood.ordinal ,
    title = title,
    description = description,
    recordingUri = recordingUri,
    maxPlaybackInSeconds = maxPlaybackInSeconds,
    dateTimeCreated = dateTimeCreated.toMillis(),
    topics = topics
)

fun JournalEntryEntityDb.toJournalEntryEntity(): JournalEntryEntity = JournalEntryEntity(
    id = id,
    mood = Mood.entries[mood] ,
    title = title,
    description = description,
    recordingUri = recordingUri,
    maxPlaybackInSeconds = maxPlaybackInSeconds,
    dateTimeCreated = dateTimeCreated.toLocalDateTime(),
    topics = topics
)

fun List<JournalEntryEntityDb>.toJournalEntries(): List<JournalEntryEntity> = map { it.toJournalEntryEntity() }