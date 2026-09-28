package com.jvcodingsolutions.echojournal.echos.presentation.echos.models

import com.jvcodingsolutions.echojournal.core.presentation.util.UiText
import com.jvcodingsolutions.echojournal.echos.presentation.models.EchoUi

data class EchoDaySection(
    val dateHeader: UiText,
    val echos: List<EchoUi>
)
