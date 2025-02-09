package com.jv23.echojournal.presentation.screens.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jv23.echojournal.domain.repository.JournalEntryRepository
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryAction
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryState
import kotlinx.coroutines.flow.MutableStateFlow


class NewEntryViewModel(
    journalEntryRepository: JournalEntryRepository
): ViewModel() {

   /* var state by mutableStateOf(NewEntryState())
        private set*/
    private val _state = MutableStateFlow(NewEntryState())
    val state = _state

    fun onAction(action: NewEntryAction) {
        when(action) {
            is NewEntryAction.OnSaveClick -> {

            }

            NewEntryAction.OnCancelNewEntry -> TODO()
            NewEntryAction.OnCreateNewTopic -> TODO()
            is NewEntryAction.OnDeleteTopic -> TODO()
            is NewEntryAction.OnDescriptionChange -> TODO()
            is NewEntryAction.OnInputTopic -> TODO()
            is NewEntryAction.OnSeekCurrentPlayback -> TODO()
            NewEntryAction.OnSelectAudioPlayer -> TODO()
            is NewEntryAction.OnSelectMoodBottomSheet -> TODO()
            is NewEntryAction.OnSelectMoodType -> TODO()
            is NewEntryAction.OnSelectTopic -> TODO()
            is NewEntryAction.OnTitleChange -> TODO()
            is NewEntryAction.OnToggleCancelDialog -> TODO()
            is NewEntryAction.OnTopicFieldFocusChange -> TODO()
        }
    }

}