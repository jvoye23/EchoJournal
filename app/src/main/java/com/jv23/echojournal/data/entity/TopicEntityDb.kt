package com.jv23.echojournal.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "journal_topics", indices = [Index(value = ["name"], unique = true)])
data class TopicEntityDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)
