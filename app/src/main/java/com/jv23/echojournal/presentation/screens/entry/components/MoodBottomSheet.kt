package com.jv23.echojournal.presentation.screens.entry.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jv23.echojournal.R
import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.presentation.core.utils.getMooUnselected
import com.jv23.echojournal.presentation.core.utils.getMoodUiByMood
import com.jv23.echojournal.presentation.core.utils.getResIdByMood
import com.jv23.echojournal.presentation.screens.home.handling.EntriesAction
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.ExcitedMood_Icon
import com.jv23.echojournal.ui.theme.NeutralMood_Icon
import com.jv23.echojournal.ui.theme.OnPrimaryContainer
import com.jv23.echojournal.ui.theme.OnSurface
import com.jv23.echojournal.ui.theme.OnSurfaceVariant
import com.jv23.echojournal.ui.theme.Outline
import com.jv23.echojournal.ui.theme.PeacefulMood_Icon
import com.jv23.echojournal.ui.theme.Primary
import com.jv23.echojournal.ui.theme.SadMood_Icon
import com.jv23.echojournal.ui.theme.StressedMood_Icon
import com.jv23.echojournal.ui.theme.SurfaceVariant
import com.jv23.echojournal.ui.theme.UnselectedExcitedMood_Icon
import com.jv23.echojournal.ui.theme.UnselectedNeutralMood_Icon
import com.jv23.echojournal.ui.theme.UnselectedPeacefulMood_Icon
import com.jv23.echojournal.ui.theme.UnselectedSadMood_Icon
import com.jv23.echojournal.ui.theme.UnselectedStressedMood_Icon

@Composable
fun MoodBottomSheet(
    initialMood: Mood?,
    onCancel: () -> Unit,
    onConfirm: (Mood) -> Unit
) {
    var selectedMood by remember(initialMood) {
        mutableStateOf(initialMood)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(293.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.how_are_you_doing),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Mood.entries.forEach {
                MoodItem(
                    mood = it,
                    isSelected = selectedMood == it,
                    onClick = {
                        selectedMood = it
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onCancel,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(R.string.cancel)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    onConfirm(selectedMood!!)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = Color.White
                ),
                modifier = Modifier.weight(1f),
                enabled = selectedMood != null
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Confirm"
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }



}

@Composable
private fun MoodItem(
    modifier: Modifier = Modifier,
    mood: Mood,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val name = getMoodUiByMood(mood).name
    val resId = if (isSelected) getResIdByMood(mood) else getMooUnselected(mood)

    Column(
        modifier = modifier
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(resId),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.surfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(min = 40.dp)
        )

    }
}

@Preview()
@Composable
private fun SelectMoodBottomSheetPreview() {
    EchoJournalTheme {
        MoodBottomSheet(
            initialMood = null,
            onCancel = {},
            onConfirm = {}
        )
    }
}