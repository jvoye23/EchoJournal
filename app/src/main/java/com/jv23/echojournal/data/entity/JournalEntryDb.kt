package com.jv23.echojournal.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jv23.echojournal.domain.data_source.model.MoodType
import java.time.Instant

@Entity(tableName = "journal_entries")
data class JournalEntryDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val moodType: MoodType,
    val audioFilePath: String,
    val audioDuration: Int,
    val amplitudeLogFilePath: String,
    val description: String,
    val topics: List<String>,
    val created: Instant

)
