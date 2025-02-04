package com.jv23.echojournal.data.repository

import com.jv23.echojournal.data.database.JournalEntryDao
import com.jv23.echojournal.data.mapper.toJournalEntries
import com.jv23.echojournal.data.mapper.toJournalEntryDb
import com.jv23.echojournal.domain.entity.JournalEntry
import com.jv23.echojournal.domain.repository.JournalEntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JournalEntryRepositoryImpl (
    private val journalEntryDao: JournalEntryDao
): JournalEntryRepository {
    override fun getJournalEntries(): Flow<List<JournalEntry>> {
        return journalEntryDao.getJournalEntries().map { it.toJournalEntries() }
    }

    override suspend fun upsertJournalEntry(journalEntry: JournalEntry) {
        return journalEntryDao.upsertJournalEntry(journalEntry.toJournalEntryDb())
    }

    override suspend fun deleteJournalEntry(journalEntry: JournalEntry) {
        return journalEntryDao.deleteJournalEntry(journalEntry.toJournalEntryDb())
    }

}