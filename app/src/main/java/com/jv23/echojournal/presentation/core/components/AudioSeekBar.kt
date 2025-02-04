package com.jv23.echojournal.presentation.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.domain.data_source.model.MoodType
import com.jv23.echojournal.ui.theme.PlayArrowFilled_Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioSeekBar(
    moodType: MoodType,
    modifier: Modifier = Modifier
) {

    val seekBarBackgroundColor = moodType.moodSeekBarBackground
    val playButtonIconColor = moodType.moodPlayIconColor
    val seekActiveBarColor = moodType.moodActiveSeekBarColor
    val seekInactiveBarColor = moodType.moodInactiveSeekBarColor
    

    Box (
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(999.dp))
            .background(seekBarBackgroundColor)
            .padding(6.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ){

            IconButton(
                modifier = Modifier.size(32.dp),
                onClick = {},
                colors = IconButtonColors(
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Red
                )

            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        ,

                    imageVector = PlayArrowFilled_Icon,
                    contentDescription = null,
                    tint = playButtonIconColor


                )

            }
            var sliderPosition by remember { mutableFloatStateOf(0f) }

            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it},
                thumb = {},
                colors = SliderColors(
                    thumbColor = Color.Transparent,
                    activeTrackColor = seekActiveBarColor,
                    activeTickColor = Color.Green,
                    inactiveTrackColor = seekInactiveBarColor,
                    inactiveTickColor = Color.Gray,
                    disabledThumbColor = Color.Cyan,
                    disabledActiveTrackColor = Color.Gray,
                    disabledActiveTickColor = Color.Gray,
                    disabledInactiveTrackColor = Color.Gray,
                    disabledInactiveTickColor = Color.Gray,
                ),



                modifier = Modifier
                    .padding(4.dp)

                    .size(width = 186.dp, height = 4.dp)


            )

            Text(
                modifier = Modifier
                    ,

                text = "0:00/12:30",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )

        }


    }
}

@Preview(showBackground = true)
@Composable
fun AudioSeekBarPreview() {
    AudioSeekBar(
        moodType = MoodType.NeutralMood
    )
}