package com.jv23.echojournal.di

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jv23.echojournal.domain.repository.JournalEntryRepository

class MyViewModel(
    private val journalEntryRepository: JournalEntryRepository
): ViewModel(){

    var state by mutableStateOf("database not synced")
        private set

    fun syncDatabase() {
       /* val data = repository.fetchData()
        database.addData(data)
        state = "database synced"*/
    }
}