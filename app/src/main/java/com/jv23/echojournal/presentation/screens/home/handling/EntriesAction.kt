package com.jv23.echojournal.presentation.screens.home.handling

import com.jv23.echojournal.domain.entity.JournalEntry

sealed interface EntriesAction {

    data class OnPLayJournalEntry(val journalEntry: JournalEntry)
    data object OnFinishRecordingClick: EntriesAction

    data object OnRecord: EntriesAction
    data object OnPauseClick: EntriesAction
    data object OnCancelClick: EntriesAction
    data object OnResumeClick: EntriesAction

    data object OnPlayClick: EntriesAction
    data object OnSelectMoodIcon: EntriesAction
    data object OnTestDiClick: EntriesAction
    data object OnToggleRecording: EntriesAction
}