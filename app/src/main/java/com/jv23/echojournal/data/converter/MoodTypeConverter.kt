package com.jv23.echojournal.data.converter

import androidx.room.TypeConverter
import com.jv23.echojournal.domain.data_source.model.MoodType

class MoodTypeConverter {

    @TypeConverter
    fun fromMoodType(moodType: MoodType): String = moodType.moodDescription

    @TypeConverter
    fun toMoodType(value: String): MoodType {
        return when (value) {
            MoodType.SadMood.moodDescription -> MoodType.SadMood
            MoodType.NeutralMood.moodDescription -> MoodType.NeutralMood
            MoodType.PeacefulMood.moodDescription -> MoodType.PeacefulMood
            MoodType.ExcitedMood.moodDescription -> MoodType.ExcitedMood
            MoodType.StressedMood.moodDescription -> MoodType.StressedMood

            else -> MoodType.UndefinedMood
        }
    }
}