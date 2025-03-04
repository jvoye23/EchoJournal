package com.jv23.echojournal.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras


fun <VW: ViewModel> viewModelFactory(initializer: (SavedStateHandle) -> VW): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val savedStateHandle = extras.createSavedStateHandle()
            return initializer(savedStateHandle) as T
        }
    }
}



