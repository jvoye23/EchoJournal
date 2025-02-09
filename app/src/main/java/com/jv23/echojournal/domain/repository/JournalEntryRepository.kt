package com.jv23.echojournal.domain.repository

import com.jv23.echojournal.domain.entity.JournalEntry
import kotlinx.coroutines.flow.Flow

interface JournalEntryRepository {

    fun getJournalEntries(): Flow<List<JournalEntry>>

    suspend fun upsertJournalEntry(journalEntry: JournalEntry)

    suspend fun deleteJournalEntry(journalEntry: JournalEntry)

    suspend fun testDi(email: String, password: String)
}