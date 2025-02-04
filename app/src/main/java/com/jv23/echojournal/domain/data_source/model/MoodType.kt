package com.jv23.echojournal.domain.data_source.model

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.jv23.echojournal.R
import com.jv23.echojournal.ui.theme.StressedMood_Icon
import com.jv23.echojournal.ui.theme.excitedOrangePlayIconColor
import com.jv23.echojournal.ui.theme.excitedOrangeSeekBarBackgroundColor
import com.jv23.echojournal.ui.theme.excitedActiveOrangeSeekBarColor
import com.jv23.echojournal.ui.theme.excitedInactiveOrangeSeekBarColor
import com.jv23.echojournal.ui.theme.neutralGreenPlayIconColor
import com.jv23.echojournal.ui.theme.neutralGreenSeekbarBackgroundColor
import com.jv23.echojournal.ui.theme.neutralInactiveGreenSeekbarColor
import com.jv23.echojournal.ui.theme.peacefulPinkPlayIconColor
import com.jv23.echojournal.ui.theme.peacefulPinkSeekBarBackgroundColor
import com.jv23.echojournal.ui.theme.peacefulActivePinkSeekBarColor
import com.jv23.echojournal.ui.theme.peacefulInactivePinkSeekBarColor
import com.jv23.echojournal.ui.theme.sadBluePlayIconColor
import com.jv23.echojournal.ui.theme.sadBlueSeekBarBackgroundColor
import com.jv23.echojournal.ui.theme.sadActiveBlueSeekBarColor
import com.jv23.echojournal.ui.theme.sadInactiveBlueSeekBarColor
import com.jv23.echojournal.ui.theme.stressedRedPlayIconColor
import com.jv23.echojournal.ui.theme.stressedRedSeekbarBackgroundColor
import com.jv23.echojournal.ui.theme.stressedActiveRedSeekbarColor
import com.jv23.echojournal.ui.theme.stressedInactiveRedSeekbarColor


import androidx.compose.ui.res.vectorResource


sealed class MoodType(
    val moodPlayIconColor: Color,
    val moodActiveSeekBarColor: Color,
    val moodInactiveSeekBarColor: Color,
    val moodSeekBarBackground: Color,
    val moodDescription: String,
    @DrawableRes val moodIcon: Int

    ) {

    data object UndefinedMood: MoodType(
        moodPlayIconColor = Color.Gray,
        moodActiveSeekBarColor = Color.Gray,
        moodInactiveSeekBarColor = Color.Gray,
        moodSeekBarBackground = Color.Gray,
        moodDescription = "Undefined",
        moodIcon = R.drawable.icon_close,
    )

    data object StressedMood: MoodType (
        moodPlayIconColor = stressedRedPlayIconColor,
        moodActiveSeekBarColor = stressedActiveRedSeekbarColor,
        moodInactiveSeekBarColor = stressedInactiveRedSeekbarColor,
        moodSeekBarBackground = stressedRedSeekbarBackgroundColor,
        moodDescription = "Stressed",
        moodIcon = R.drawable.icon_mood_stressed,




    )
    data object SadMood: MoodType (
        moodPlayIconColor = sadBluePlayIconColor,
        moodActiveSeekBarColor = sadActiveBlueSeekBarColor,
        moodInactiveSeekBarColor = sadInactiveBlueSeekBarColor,
        moodSeekBarBackground = sadBlueSeekBarBackgroundColor,
        moodDescription = "Sad",
        moodIcon = R.drawable.icon_mood_sad
    )
    data object NeutralMood: MoodType (
        moodPlayIconColor = neutralGreenPlayIconColor,
        moodActiveSeekBarColor = neutralInactiveGreenSeekbarColor,
        moodInactiveSeekBarColor = neutralInactiveGreenSeekbarColor,
        moodSeekBarBackground = neutralGreenSeekbarBackgroundColor,
        moodDescription = "Neutral",
        moodIcon = R.drawable.icon_mood_neutral
    )
    data object PeacefulMood: MoodType (
        moodPlayIconColor = peacefulPinkPlayIconColor,
        moodActiveSeekBarColor = peacefulActivePinkSeekBarColor,
        moodInactiveSeekBarColor = peacefulInactivePinkSeekBarColor,
        moodSeekBarBackground = peacefulPinkSeekBarBackgroundColor,
        moodDescription = "Sad",
        moodIcon = R.drawable.icon_mood_peaceful
    )
    data object ExcitedMood: MoodType (
        moodPlayIconColor = excitedOrangePlayIconColor,
        moodActiveSeekBarColor = excitedActiveOrangeSeekBarColor,
        moodInactiveSeekBarColor = excitedInactiveOrangeSeekBarColor,
        moodSeekBarBackground = excitedOrangeSeekBarBackgroundColor,
        moodDescription = "Sad",
        moodIcon = R.drawable.icon_mood_excited
    )

}

