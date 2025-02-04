package com.jv23.echojournal.data.mapper

import com.jv23.echojournal.data.entity.JournalEntryDb
import com.jv23.echojournal.domain.entity.JournalEntry

fun JournalEntry.toJournalEntryDb(): JournalEntryDb = JournalEntryDb(
    id = id,
    title = title,
    moodType = moodType,
    audioFilePath = audioFilePath,
    audioDuration = audioDuration,
    amplitudeLogFilePath = amplitudeLogFilePath,
    description = description,
    topics = topics,
    created = created
)

fun JournalEntryDb.toJournalEntry(): JournalEntry = JournalEntry(
    id = id,
    title = title,
    moodType = moodType,
    audioFilePath = audioFilePath,
    audioDuration = audioDuration,
    amplitudeLogFilePath = amplitudeLogFilePath,
    description = description,
    topics = topics,
    created = created
)

fun List<JournalEntryDb>.toJournalEntries(): List<JournalEntry> = map { it.toJournalEntry() }