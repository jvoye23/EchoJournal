package com.jv23.echojournal.presentation.screens.entry.handling

import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.domain.data_source.model.MoodType

data class NewEntryState(
    val title: String = "",
    val description: String = "",
    val selectedMood: Mood? = null,
    val isSelectMoodBottomSheetOpened: Boolean = false,
    val canSave: Boolean = false,
    val isPlaying: Boolean = false,
    val curPlaybackInSeconds: Long = 0,
    val maxPlaybackInSeconds: Long = 0,
    val isCancelCreateJournalEntryDialogOpened: Boolean = false,
    val inputTopic: String = "",
    val isNewTopic: Boolean = false,
    val isTopicFieldFocused: Boolean = false,
    val unselectedTopics: Set<String> = emptySet(),
    val selectedTopics: Set<String> = emptySet(),

)
