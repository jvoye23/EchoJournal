package com.jv23.echojournal.presentation.screens.home.handling

sealed interface EntriesAction {

    data object OnRecord: EntriesAction
    data object OnPauseClick: EntriesAction
    data object OnCancelClick: EntriesAction
    data object OnResumeClick: EntriesAction
    data object OnSaveClick: EntriesAction
    data object OnPlayClick: EntriesAction
    data object OnSelectMoodIcon: EntriesAction
}