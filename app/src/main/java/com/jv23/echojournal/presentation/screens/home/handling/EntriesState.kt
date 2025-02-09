package com.jv23.echojournal.presentation.screens.home.handling

import com.jv23.echojournal.domain.entity.JournalEntry
import java.time.LocalDate

data class EntriesState(
    val dateToJournalsMap: Map<LocalDate, List<JournalEntry>> = emptyMap(),
    val canRecord: Boolean = false,
    val isAudioRecorderBottomSheetOpened: Boolean = false,
    val hasStartedRecording: Boolean = false,
    val isRecording: Boolean = false,
    val durationInSeconds: Long = 0L,
    val currentFilePlaying: String? = null,
    val isPlaying: Boolean = false,
    val curPlaybackInSeconds: Long = 0,
   // val filteredTopics: JournalFilterType.Topics = JournalFilterType.Topics(emptySet()),
    //val filteredMoods: JournalFilterType.Moods = getMoodsFilterType()

)
