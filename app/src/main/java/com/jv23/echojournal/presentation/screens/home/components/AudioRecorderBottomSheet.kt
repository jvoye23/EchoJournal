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
import com.jv23.echojournal.presentation.screens.entry.handling.EntriesAction
import com.jv23.echojournal.ui.theme.Close_Icon
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Pause_Icon
import com.jv23.echojournal.ui.theme.Primary90
import com.jv23.echojournal.ui.theme.Primary95

@Composable
fun AudioRecorderBottomSheet(
    modifier: Modifier = Modifier,
    isRecording: Boolean,
    timer: String,
    onEvent: (EntriesAction) -> Unit
){
    Column(
        modifier = modifier
            .width(412.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row(
            modifier = modifier
                .width(288.dp),

            horizontalArrangement = Arrangement.Center


        ) {
            Text(
                text = stringResource(R.string.recording_your_memories),
                modifier = Modifier,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Row(
            modifier = modifier
                .width(288.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = timer.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row (
            modifier = modifier
                .width(300.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(

                modifier = modifier
                    .clickable {onEvent(EntriesAction.OnPlayClick) }
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    imageVector = Close_Icon,
                    contentDescription = stringResource(R.string.close_icon),
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )

            }

            RecordingSaveButton(
                onEvent = onEvent
            )
            Box(
                modifier = modifier
                    .clickable { }
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEEF0FF)),
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    imageVector = Pause_Icon,
                    contentDescription = stringResource(R.string.pause_icon),
                    modifier = modifier
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun RecordingSaveButton(
    onEvent: (EntriesAction) -> Unit,
    modifier: Modifier = Modifier,
   // color: Color = Color (0xFF578CFF),

){
    Box(
        modifier = modifier
    ) {
        Box (
            modifier = modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(Primary95),
            contentAlignment = Alignment.Center
        ){
            Box (
                modifier = modifier
                    .size(108.dp)
                    .clip(CircleShape)
                    .background(Primary90),
                contentAlignment = Alignment.Center


            ) {
                Box(
                    modifier = modifier
                        .size(72.dp) // Adjust the size as needed
                        .clip(CircleShape)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF578CFF), Color(0xFF1F70F5)),
                                start = Offset(0f, 0f),  // Optional: Start point (default: top-left)
                                end = Offset(1f, 1f)    // Optional: End point (default: bottom-right)
                            )
                        )
                        .clickable { onEvent(EntriesAction.OnSaveClick) },
                    contentAlignment = Alignment.Center // Center the icon
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = stringResource(R.string.check_button),
                        tint = Color.White,
                        modifier = modifier
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
            modifier = Modifier,
            isRecording = false,
            timer = "01:30:45",
            onEvent = {}
        )

    }
}