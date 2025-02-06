package com.jv23.echojournal.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jv23.echojournal.data.database.JournalEntryDatabase
import com.jv23.echojournal.data.repository.JournalEntryRepositoryImpl
import com.jv23.echojournal.domain.repository.JournalEntryRepository
import com.jv23.echojournal.presentation.screens.entry.NewEntryViewModel
import com.jv23.echojournal.presentation.screens.home.EntriesViewModel

class AppDataContainer(private val applicationContext: Context): AppContainer {

    override val journalEntryRepository:
            JournalEntryRepository by lazy {
                JournalEntryRepositoryImpl(JournalEntryDatabase
                    .getDatabase(applicationContext).getJournalEntryDao())

    }
    override val myViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory {
            MyViewModel(
                journalEntryRepository = journalEntryRepository
            )
        }
    override val entriesViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory {
            EntriesViewModel(application = Application())
        }


    override val newEntryViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory {
            NewEntryViewModel()
        }


}