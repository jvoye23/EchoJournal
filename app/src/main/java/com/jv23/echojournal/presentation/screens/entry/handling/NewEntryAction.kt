package com.jv23.echojournal.presentation.screens.entry.handling

import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.domain.data_source.model.MoodType

sealed interface NewEntryAction {
    data class OnToggleSelectMoodBottomSheet(val isOpen: Boolean): NewEntryAction
    data class OnSelectMoodBottomSheet (val isOpen: Boolean): NewEntryAction
    data class OnSelectMood(val mood: Mood): NewEntryAction
    data class OnTitleChange(val value: String): NewEntryAction
    data class OnDescriptionChange(val value: String): NewEntryAction
    data object OnSelectAudioPlayer: NewEntryAction
    data object OnCreateNewTopic: NewEntryAction
    data class OnTopicFieldFocusChange(val isFocused: Boolean): NewEntryAction
    data class OnDeleteTopic(val value: String): NewEntryAction
    data class OnSelectTopic(val value: String): NewEntryAction
    data class OnInputTopic(val value: String): NewEntryAction
    data class OnToggleCancelDialog(val isOpen: Boolean): NewEntryAction
    data object OnCancelNewEntry: NewEntryAction
    data object OnSaveClick: NewEntryAction
    data class OnSeekCurrentPlayback(val seconds: Int): NewEntryAction
    data object OnAddNewTopic: NewEntryAction


}