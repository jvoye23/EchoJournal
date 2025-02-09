package com.jv23.echojournal.presentation.screens.home.handling

sealed interface EntriesEvent {
    data class NewEntrySuccess(val id: String, val fileUri: String): EntriesEvent
    data class NewEntryError(val text: String): EntriesEvent
}