package com.jv23.echojournal.presentation.screens.home.handling


import com.jv23.echojournal.domain.entity.JournalEntryEntity

sealed interface EntriesAction {

    data class OnTogglePlayback(val journalEntry: JournalEntryEntity): EntriesAction
    data object OnFinishRecordingClick: EntriesAction

    data object OnToggleRecord: EntriesAction
    data object OnPauseClick: EntriesAction
    data object OnCancelClick: EntriesAction
    data object OnResumeClick: EntriesAction


    data object OnSelectMoodIcon: EntriesAction
    data object OnTestDiClick: EntriesAction

    data class OnToggleAudioRecorderBottomSheet(val isOpen: Boolean): EntriesAction
    data class OnSeekCurrentPlayback(val seconds: Int): EntriesAction
}