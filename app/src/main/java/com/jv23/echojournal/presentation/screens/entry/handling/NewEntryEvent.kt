package com.jv23.echojournal.presentation.screens.entry.handling

sealed interface NewEntryEvent {
    data object Success: NewEntryEvent
    data class Error(val error: String): NewEntryEvent
    data object NavigateBack: NewEntryEvent
}