package com.jvcodingsolutions.echojournal.echos.presentation.create_echo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvcodingsolutions.echojournal.echos.presentation.echos.EchosEvent
import com.jvcodingsolutions.echojournal.echos.presentation.models.MoodUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CreateEchoViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CreateEchoState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = CreateEchoState()
        )

    fun onAction(action: CreateEchoAction) {
        when (action) {
            is CreateEchoAction.OnAddTopicTextChange -> {}
            CreateEchoAction.OnCancelClick -> {}
            CreateEchoAction.OnConfirmMood -> onConfirmMood()
            CreateEchoAction.OnCreateNewTopicClick -> {}
            CreateEchoAction.OnDismissMoodSelector -> onDismissMoodSelector()
            CreateEchoAction.OnDismissTopicSuggestions -> {}
            is CreateEchoAction.OnMoodClick -> onMoodClick(action.moodUi)
            CreateEchoAction.OnNavigateBackClick -> {}
            is CreateEchoAction.OnNoteTextChange -> {}
            CreateEchoAction.OnPauseAudioClick -> {}
            CreateEchoAction.OnPlayAudioClick -> {}
            is CreateEchoAction.OnRemoveTopicClick -> {}
            CreateEchoAction.OnSaveClick -> {}
            is CreateEchoAction.OnTitleTextChange -> {}
            is CreateEchoAction.OnTopicClick -> {}
            is CreateEchoAction.OnTrackSizeAvailable -> {}
            CreateEchoAction.OnSelectMoodClick -> onSelectMoodClick()
            CreateEchoAction.ShowMoodSelector -> {}
        }
    }

    private fun onConfirmMood() {
        _state.update { it.copy(
            mood = it.selectedMood,
            canSaveEcho = it.titleText.isNotBlank(),
            showMoodSelector = false
        ) }
    }

    private fun onDismissMoodSelector() {
        _state.update { it.copy(
            showMoodSelector = false
        ) }
    }

    private fun onSelectMoodClick() {
        _state.update { it.copy(
            showMoodSelector = true
        ) }
    }

    private fun onMoodClick(mood: MoodUi) {
        _state.update { it.copy(
            selectedMood = mood
        ) }
    }
}