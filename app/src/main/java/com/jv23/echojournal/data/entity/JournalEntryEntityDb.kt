package com.jv23.echojournal.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jv23.echojournal.domain.data_source.model.Mood

@Entity(tableName = "journal_entries")
data class JournalEntryEntityDb(
    @PrimaryKey
    val id: String,
    val mood: Int,
    val title: String,
    val description: String,
    val recordingUri: String,
    val maxPlaybackInSeconds: Long,
    val dateTimeCreated: Long,
    val topics: Set<String>

)
