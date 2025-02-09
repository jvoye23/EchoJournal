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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jv23.echojournal.EchoJournalApplication
import com.jv23.echojournal.R
import com.jv23.echojournal.presentation.screens.home.components.AppFloatingActionButton
import com.jv23.echojournal.presentation.core.components.AppTopAppBar
import com.jv23.echojournal.presentation.core.utils.ObserveAsEvents
import com.jv23.echojournal.presentation.core.utils.showToastStr
import com.jv23.echojournal.presentation.screens.home.handling.EntriesAction
import com.jv23.echojournal.presentation.screens.home.handling.EntriesState
import com.jv23.echojournal.presentation.screens.home.components.AudioRecorderBottomSheet
import com.jv23.echojournal.presentation.screens.home.handling.EntriesEvent
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Faces_Icon


@Composable
fun EntriesScreenRoot(
    onNavigateToNewEntryScreen: (id: String, fileUri: String) -> Unit,
    viewModel: EntriesViewModel = viewModel<EntriesViewModel>(
        factory = EchoJournalApplication.container.entriesViewModelFactory)
    ) {
        val context = LocalContext.current
        val state by viewModel.state.collectAsStateWithLifecycle()

        ObserveAsEvents(viewModel.events) { entriesEvent ->
            when(entriesEvent) {
                is EntriesEvent.NewEntryError -> {
                    context.showToastStr(entriesEvent.text)

                }
                is EntriesEvent.NewEntrySuccess -> {
                    onNavigateToNewEntryScreen(entriesEvent.id, entriesEvent.fileUri)
                }
            }
        }

        EntriesScreen(
            state = state,
            onAction = viewModel::onAction
        )
    }


@Composable
private fun EntriesScreen(
    state: EntriesState,
    onAction: (EntriesAction) -> Unit

) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    /*var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }*/

    Scaffold (
        floatingActionButton = {
            AppFloatingActionButton(
                modifier = Modifier,
                onClick = {
                    //isSheetOpen = true

                    onAction(EntriesAction.OnRecord)
                    state.copy(
                        isAudioRecorderBottomSheetOpened = true
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

                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    onClick = {
                        onAction(EntriesAction.OnTestDiClick)

                    }
                ) {
                    Text(text = "Test DI")
                }

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

        if(state.isAudioRecorderBottomSheetOpened) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    state.copy(
                        isAudioRecorderBottomSheetOpened = false
                    )
                }
            ) {
                AudioRecorderBottomSheet(
                    modifier = Modifier,
                    onAction = onAction,

                    durationInSeconds = 0,
                    state = state
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

            ),
            onAction = {}
        )
    }
}


