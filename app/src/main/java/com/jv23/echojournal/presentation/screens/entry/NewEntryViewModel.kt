package com.jv23.echojournal.presentation.screens.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryAction
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryState


class NewEntryViewModel: ViewModel() {

    var state by mutableStateOf(NewEntryState())
        private set

    fun onAction(action: NewEntryAction) {
        when(action) {
            is NewEntryAction.OnSaveClick -> {

            }
        }
    }

}