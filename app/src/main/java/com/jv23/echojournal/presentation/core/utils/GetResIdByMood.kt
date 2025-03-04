package com.jv23.echojournal.presentation.core.utils

import androidx.annotation.DrawableRes
import com.jv23.echojournal.R
import com.jv23.echojournal.domain.data_source.model.Mood

fun getResIdByMood(mood: Mood): Int {
    return when (mood) {
        Mood.EXCITED -> R.drawable.icon_mood_excited
        Mood.PEACEFUL -> R.drawable.icon_mood_peaceful
        Mood.NEUTRAL -> R.drawable.icon_mood_neutral
        Mood.SAD -> R.drawable.icon_mood_sad
        Mood.STRESSED -> R.drawable.icon_mood_stressed
    }
}

fun getMooUnselected(mood: Mood): Int {
    return when (mood) {
        Mood.EXCITED -> R.drawable.unselected_icon_mood_excited
        Mood.PEACEFUL -> R.drawable.unselected_icon_mood_peaceful
        Mood.NEUTRAL -> R.drawable.unselected_icon_mood_neutral
        Mood.SAD -> R.drawable.unselected_icon_mood_sad
        Mood.STRESSED -> R.drawable.unselected_icon_mood_stressed
    }
}

fun getMoodUiByMood(mood: Mood): MoodUi {
    val resId = getResIdByMood(mood)
    val name = when (mood) {
        Mood.EXCITED -> "Excited"
        Mood.PEACEFUL -> "Peaceful"
        Mood.NEUTRAL -> "Neutral"
        Mood.SAD -> "Sad"
        Mood.STRESSED -> "Stressed"
    }

    return MoodUi(
        resId = resId,
        name = name,
        isSelected = false
    )
}

fun getMoodByName(value: String): Mood? {
    return when (value) {
        "Excited" -> Mood.EXCITED
        "Peaceful" -> Mood.PEACEFUL
        "Neutral" -> Mood.NEUTRAL
        "Sad" -> Mood.SAD
        "Stressed" -> Mood.STRESSED
        else -> null
    }
}

data class MoodUi(
    @DrawableRes val resId: Int,
    val name: String,
    val isSelected: Boolean
)