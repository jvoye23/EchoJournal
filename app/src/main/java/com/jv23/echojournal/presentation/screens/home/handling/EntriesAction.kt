package com.jv23.echojournal.presentation.screens.home.handling


import com.jv23.echojournal.domain.entity.JournalEntryEntity

sealed interface EntriesAction {

    data class OnPLayJournalEntry(val journalEntry: JournalEntryEntity)
    data object OnFinishRecordingClick: EntriesAction

    data object OnToggleRecord: EntriesAction
    data object OnPauseClick: EntriesAction
    data object OnCancelClick: EntriesAction
    data object OnResumeClick: EntriesAction

    data object OnPlayClick: EntriesAction
    data object OnSelectMoodIcon: EntriesAction
    data object OnTestDiClick: EntriesAction
    data object OnToggleRecording: EntriesAction
   data object OnToggleAudioRecorderBottomSheet: EntriesAction
}