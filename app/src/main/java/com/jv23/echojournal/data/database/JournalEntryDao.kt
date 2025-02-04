package com.jv23.echojournal.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jv23.echojournal.data.entity.JournalEntryDb
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalEntryDao {

    @Query("SELECT * FROM journal_entries ORDER BY created DESC" )
    fun getJournalEntries(): Flow<List<JournalEntryDb>>

    @Upsert
    suspend fun upsertJournalEntry(journalEntryDb: JournalEntryDb)

    @Delete
    suspend fun deleteJournalEntry(journalEntryDb: JournalEntryDb)
}