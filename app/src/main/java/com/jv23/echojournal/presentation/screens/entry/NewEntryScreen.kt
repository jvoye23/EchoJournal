package com.jv23.echojournal.presentation.screens.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jv23.echojournal.EchoJournalApplication
import com.jv23.echojournal.R
import com.jv23.echojournal.presentation.core.components.AudioSeekBar
import com.jv23.echojournal.domain.data_source.model.MoodType
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryAction
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryState
import com.jv23.echojournal.presentation.screens.home.EntriesViewModel
import com.jv23.echojournal.presentation.screens.home.handling.EntriesAction
import com.jv23.echojournal.presentation.screens.home.handling.EntriesState
import com.jv23.echojournal.ui.theme.Add_Icon
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Hashtag_Icon
import com.jv23.echojournal.ui.theme.ModeEditOutline_Icon
import com.jv23.echojournal.ui.theme.OnPrimaryContainer
import com.jv23.echojournal.ui.theme.OutLineVariant
import com.jv23.echojournal.ui.theme.Outline
import com.jv23.echojournal.ui.theme.Primary
import com.jv23.echojournal.ui.theme.SurfaceVariant

@Composable
fun NewEntryScreenRoot(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewEntryViewModel = viewModel<NewEntryViewModel>(
        factory = EchoJournalApplication.container.newEntryViewModelFactory)
    ) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    NewEntryScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun NewEntryScreen(
    modifier: Modifier,
    state: NewEntryState,
    onAction: (NewEntryAction) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 24.dp),

    ){
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = modifier
                        .clickable { }
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.errorContainer),
                    contentAlignment = Alignment.Center

                ) {
                    Icon(
                        imageVector = Add_Icon,
                        contentDescription = stringResource(R.string.close_icon),
                        modifier = modifier
                            .size(20.dp),
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )

                }
                Spacer(modifier = modifier.width(12.dp))
                BasicTextField(
                    value = state.title,
                    onValueChange = {onAction(NewEntryAction.OnTitleChange(it))
                    },
                    textStyle = MaterialTheme.typography.headlineLarge,
                    decorationBox = {innerTextField ->
                        Box(modifier = Modifier.weight(1f)
                        ) {
                            if (state.title.isBlank()){
                                Text(
                                    text = stringResource(R.string.add_title),
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = OutLineVariant
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            AudioSeekBar(
                modifier = modifier
                    .fillMaxWidth(),
                moodType = MoodType.PeacefulMood
            )
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.size(16.dp),
                    imageVector = Hashtag_Icon,
                    tint = OutLineVariant,
                    contentDescription = stringResource(R.string.hashtag_icon)
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.topic),
                    style = MaterialTheme.typography.bodyMedium,
                    color = OutLineVariant
                )

            }
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier.size(16.dp),
                    tint = OutLineVariant,
                    imageVector = ModeEditOutline_Icon,
                    contentDescription = stringResource(R.string.edit_icon)
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    text = stringResource(R.string.add_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = OutLineVariant
                )

            }        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Button(
                modifier = modifier
                    .width(101.dp)
                    .height(44.dp),
                colors = ButtonColors(
                    containerColor = OnPrimaryContainer,
                    contentColor = Primary,
                    disabledContainerColor = SurfaceVariant,
                    disabledContentColor = Outline
                ),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = modifier.width(10.dp))
            Button(
                modifier = modifier
                    .width(263.dp)
                    .height(44.dp),
                enabled = false,
                colors = ButtonColors(
                    containerColor = OnPrimaryContainer,
                    contentColor = Primary,
                    disabledContainerColor = SurfaceVariant,
                    disabledContentColor = Outline
                ),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.save),
                    fontSize = 16.sp
                )
            }
        }

    }

}

@Preview()
@Composable
fun EntryScreenPreview() {
    EchoJournalTheme {
        NewEntryScreen(
            modifier = Modifier,
            state = NewEntryState(

            ),
            onAction = {}
        )

    }
}