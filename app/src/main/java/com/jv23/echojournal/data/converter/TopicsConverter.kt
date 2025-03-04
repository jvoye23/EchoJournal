package com.jv23.echojournal.data.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class TopicsConverter {

    @TypeConverter
    fun fromTopicsList(topics: List<String>): String {
        return topics.joinToString(",")
    }

    @TypeConverter
    fun toTopicsList(data: String): List<String> {
        return data.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    }


    @TypeConverter
    fun setToString(value: Set<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun stringToSet(value: String): Set<String> {
        return Json.decodeFromString<Set<String>>(value)
    }
}