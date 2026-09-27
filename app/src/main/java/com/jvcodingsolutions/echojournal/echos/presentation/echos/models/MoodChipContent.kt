package com.jvcodingsolutions.echojournal.echos.presentation.echos.models

import com.jvcodingsolutions.echojournal.R
import com.jvcodingsolutions.echojournal.core.presentation.util.UiText

data class MoodChipContent(
    val iconsRes: List<Int> = emptyList(),
    val title: UiText = UiText.StringResource(R.string.all_moods)
)
