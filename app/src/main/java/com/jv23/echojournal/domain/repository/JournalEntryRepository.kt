package com.jv23.echojournal.domain.repository

import com.jv23.echojournal.domain.entity.JournalEntryEntity
import kotlinx.coroutines.flow.Flow

interface JournalEntryRepository {

    fun getAllJournalEntries(): Flow<List<JournalEntryEntity>>

    suspend fun upsertJournalEntry(journalEntry: JournalEntryEntity)

    suspend fun deleteJournalEntryById(id: String)

    suspend fun deleteAllJournalEntries()

    suspend fun upsertTopic(name: String)

    fun getAllTopics(): Flow<Set<String>>

    suspend fun testDi(email: String, password: String)
}