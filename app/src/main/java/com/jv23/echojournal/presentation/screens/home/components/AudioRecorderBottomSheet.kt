package com.jv23.echojournal.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.R
import com.jv23.echojournal.presentation.core.utils.formatSecondsToHourMinuteSecond
import com.jv23.echojournal.ui.theme.Close_Icon
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Mic_Icon
import com.jv23.echojournal.ui.theme.Pause_Icon
import com.jv23.echojournal.ui.theme.Primary90
import com.jv23.echojournal.ui.theme.Primary95
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
fun AudioRecorderBottomSheet(
    hasStartedRecording: Boolean,
    isRecording: Boolean,
    durationInSeconds: Long,
    onToggleRecording: () -> Unit,
    onPauseRecording: () -> Unit,
    onCancelRecording: () -> Unit,
    onFinishRecording: () -> Unit
){
    Column(
        modifier = Modifier
            .width(412.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = Modifier
                .width(288.dp),

            horizontalArrangement = Arrangement.Center


        ) {
            Text(
                text = if (!isRecording){
                    stringResource(R.string.recording_your_memories)
                } else {
                    if(hasStartedRecording){
                        stringResource(R.string.recording_paused)
                    } else {
                        stringResource(R.string.recording_not_started_yet)
                    }
                },
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .width(288.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = formatSecondsToHourMinuteSecond(durationInSeconds),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = Modifier
                .width(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .clickable { onCancelRecording() },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    imageVector = Close_Icon,
                    contentDescription = stringResource(R.string.close_icon),
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )

            }

            if (!hasStartedRecording){
                RecordingSaveButton(
                    onToggle = { onToggleRecording() },

                )
            } else {
                ContinueRecordingButton(
                    onToggle = {
                        onFinishRecording()
                    }
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEEF0FF))
                    .clickable(
                        enabled = hasStartedRecording
                    ) {
                       onPauseRecording()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if(isRecording) Pause_Icon else Icons.Filled.Check,
                    contentDescription = stringResource(R.string.pause_icon),
                    modifier = Modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ContinueRecordingButton(
    onToggle: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(72.dp) // Adjust the size as needed
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF578CFF), Color(0xFF1F70F5)),
                    start = Offset(
                        0f,
                        0f
                    ),  // Optional: Start point (default: top-left)
                    end = Offset(
                        1f,
                        1f
                    )    // Optional: End point (default: bottom-right)
                )
            )
            .clickable {
                onToggle()

            },
        contentAlignment = Alignment.Center // Center the icon
    ) {
        Icon(
            imageVector = Mic_Icon,
            contentDescription = stringResource(R.string.mic_button),
            tint = Color.White,
            modifier = Modifier
                .size(32.dp)
        )
    }

}

@Composable
fun RecordingSaveButton(
    onToggle: () -> Unit
   // color: Color = Color (0xFF578CFF),

){
    Box(
        modifier = Modifier
    ) {
        Box (
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(Primary95),
            contentAlignment = Alignment.Center
        ){
            Box (
                modifier = Modifier
                    .size(108.dp)
                    .clip(CircleShape)
                    .background(Primary90),
                contentAlignment = Alignment.Center

            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp) // Adjust the size as needed
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF578CFF), Color(0xFF1F70F5)),
                                start = Offset(
                                    0f,
                                    0f
                                ),  // Optional: Start point (default: top-left)
                                end = Offset(
                                    1f,
                                    1f
                                )    // Optional: End point (default: bottom-right)
                            )
                        )
                        .clickable {
                            onToggle()

                        },
                    contentAlignment = Alignment.Center // Center the icon
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(R.string.check_button),
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }

            }

        }

    }

}

@Preview
@Composable
private fun AudioRecorderBottomSheetPreview(){
    EchoJournalTheme {
        AudioRecorderBottomSheet(
            durationInSeconds = (1.hours + 45.minutes + 24.seconds).inWholeSeconds,
            hasStartedRecording = false,
            isRecording = false,
            onToggleRecording = {},
            onPauseRecording = {},
            onCancelRecording = {},
            onFinishRecording = {},
        )

    }
}
