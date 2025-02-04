@file:OptIn(ExperimentalMaterial3Api::class)

package com.jv23.echojournal.presentation.screens.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.R
import com.jv23.echojournal.presentation.screens.home.components.AppFloatingActionButton
import com.jv23.echojournal.presentation.core.components.AppTopAppBar
import com.jv23.echojournal.presentation.screens.entry.handling.EntriesAction
import com.jv23.echojournal.presentation.screens.entry.handling.EntriesState
import com.jv23.echojournal.presentation.screens.home.components.AudioRecorderBottomSheet
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Faces_Icon


@Composable
fun EntriesScreenRoot(

    viewModel: EntriesViewModel,
    modifier: Modifier = Modifier

) {
    EntriesScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}


@Composable
private fun EntriesScreen(
    state: EntriesState,
    onAction: (EntriesAction) -> Unit

) {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold (
        floatingActionButton = {
            AppFloatingActionButton(
                modifier = Modifier,
                onClick = {
                    isSheetOpen = true
                    onAction(EntriesAction.OnRecord)
                    state.copy(
                        isEntriesListEmpty = true,
                        isRecording = true
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->

        AppTopAppBar()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Image(
                    imageVector = Faces_Icon,
                    contentDescription = stringResource(id = R.string.faces_icon),
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.no_entries),
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = stringResource(id = R.string.start_recording),
                    style = MaterialTheme.typography.bodyMedium,
                )

                /*val categories = listOf("Work", "Hobby", "Finance", "Home")
                EntryCard(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    moodType = MoodType.PeacefulMood,
                    title = "Mein Journal",
                    timeOfRecord = "18:59",
                    audioLogDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tit amet, consectetur adipiscing elit, sed t... Show more",
                    categories = categories
                )*/


            }
        }

        if(isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    isSheetOpen = false
                }
            ) {
                AudioRecorderBottomSheet(
                    modifier = Modifier,
                    isRecording = false,
                    timer = "01:45:30",
                    onEvent = onAction
                )
            }
        }
    }
}

@Preview()
@Composable
private fun EntriesScreenPreview() {
    EchoJournalTheme {
        EntriesScreen(
            state = EntriesState(
                isEntriesListEmpty = true
            ),
            onAction = {}
        )
    }
}


