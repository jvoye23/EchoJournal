package com.jv23.echojournal.di

import androidx.lifecycle.ViewModelProvider
import com.jv23.echojournal.domain.repository.JournalEntryRepository

interface AppContainer {

    val journalEntryRepository: JournalEntryRepository
    val myViewModelFactory: ViewModelProvider.Factory
    val entriesViewModelFactory: ViewModelProvider.Factory
    val newEntryViewModelFactory: ViewModelProvider.Factory


}