package com.jv23.echojournal.presentation.core.utils

import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.presentation.core.components.MoodColors
import com.jv23.echojournal.ui.theme.ExcitedContainer
import com.jv23.echojournal.ui.theme.ExcitedPrimary
import com.jv23.echojournal.ui.theme.ExcitedSecondary
import com.jv23.echojournal.ui.theme.NeutralContainer
import com.jv23.echojournal.ui.theme.NeutralPrimary
import com.jv23.echojournal.ui.theme.NeutralSecondary
import com.jv23.echojournal.ui.theme.PeacefulContainer
import com.jv23.echojournal.ui.theme.PeacefulPrimary
import com.jv23.echojournal.ui.theme.PeacefulSecondary
import com.jv23.echojournal.ui.theme.SadContainer
import com.jv23.echojournal.ui.theme.SadPrimary
import com.jv23.echojournal.ui.theme.SadSecondary
import com.jv23.echojournal.ui.theme.StressedContainer
import com.jv23.echojournal.ui.theme.StressedPrimary
import com.jv23.echojournal.ui.theme.StressedSecondary

fun getMoodColors(mood: Mood): MoodColors {
    return when (mood) {
        Mood.EXCITED -> MoodColors(
            primary = ExcitedPrimary,
            secondary = ExcitedSecondary,
            container = ExcitedContainer
        )
        Mood.PEACEFUL -> MoodColors(
            primary = PeacefulPrimary,
            secondary = PeacefulSecondary,
            container = PeacefulContainer
        )
        Mood.NEUTRAL -> MoodColors(
            primary = NeutralPrimary,
            secondary = NeutralSecondary,
            container = NeutralContainer
        )
        Mood.SAD -> MoodColors(
            primary = SadPrimary,
            secondary = SadSecondary,
            container = SadContainer
        )
        Mood.STRESSED -> MoodColors(
            primary = StressedPrimary,
            secondary = StressedSecondary,
            container = StressedContainer
        )
    }
}