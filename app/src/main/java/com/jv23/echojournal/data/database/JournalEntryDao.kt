package com.jv23.echojournal.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jv23.echojournal.data.entity.JournalEntryEntityDb
import com.jv23.echojournal.data.entity.TopicEntityDb
import com.jv23.echojournal.domain.entity.JournalEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalEntryDao {

    @Query("SELECT * FROM journal_entries ORDER BY dateTimeCreated DESC" )
    fun getAllJournalEntries(): Flow<List<JournalEntryEntityDb>>

    @Upsert
    suspend fun upsertJournalEntry(journalEntry: JournalEntryEntityDb)

    @Upsert
    suspend fun upsertTopic(topic: TopicEntityDb)

    @Query("SELECT * FROM journal_topics")
    fun getAllTopics(): Flow<List<TopicEntityDb>>

    @Query("DELETE FROM journal_topics WHERE id = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun deleteJournalEntry(journalEntry: JournalEntryEntityDb)

    @Query("DElETE FROM journal_entries")
    suspend fun deleteAll()




}