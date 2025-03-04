package com.jv23.echojournal.data.repository

import com.jv23.echojournal.data.database.JournalEntryDao
import com.jv23.echojournal.data.entity.TopicEntityDb
import com.jv23.echojournal.data.mapper.toJournalEntries
import com.jv23.echojournal.data.mapper.toJournalEntryEntityDb
import com.jv23.echojournal.domain.entity.JournalEntryEntity
import com.jv23.echojournal.domain.repository.JournalEntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class JournalEntryRepositoryImpl (
    private val journalEntryDao: JournalEntryDao
): JournalEntryRepository {
    override fun getAllJournalEntries(): Flow<List<JournalEntryEntity>> {
        return journalEntryDao.getAllJournalEntries().map { it.toJournalEntries() }
    }

    override suspend fun upsertJournalEntry(journalEntry: JournalEntryEntity) {
        return journalEntryDao.upsertJournalEntry(journalEntry.toJournalEntryEntityDb())
    }

    override suspend fun deleteJournalEntryById(id: String) {
        journalEntryDao.deleteById(id)
    }

    override suspend fun deleteAllJournalEntries() {
        journalEntryDao.deleteAll()
    }

    override suspend fun upsertTopic(name: String) {
        val newTopic = TopicEntityDb(name = name)
        journalEntryDao.upsertTopic(newTopic)
    }

    override fun getAllTopics(): Flow<Set<String>> {
        return journalEntryDao
            .getAllTopics()
            .map { entities ->
                entities.map { it.name }.toSet()
            }
    }



    override suspend fun testDi(email: String, password: String) {
        try {
            println("Testing DI...")
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

}