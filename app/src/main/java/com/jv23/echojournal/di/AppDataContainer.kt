package com.jv23.echojournal.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jv23.echojournal.data.database.JournalEntryDatabase
import com.jv23.echojournal.data.repository.JournalEntryRepositoryImpl
import com.jv23.echojournal.domain.repository.JournalEntryRepository

class AppDataContainer(private val context: Context): AppContainer {
    override val journalEntryRepository:
            JournalEntryRepository by lazy {
                JournalEntryRepositoryImpl(JournalEntryDatabase
                    .getDatabase(context).getJournalEntryDao())

    }
    override val myViewModelFactory: ViewModelProvider.Factory
        get() = viewModelFactory {
            MyViewModel(
                journalEntryRepository = journalEntryRepository
            )
        }


}