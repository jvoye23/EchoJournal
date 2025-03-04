package com.jv23.echojournal.presentation.screens.entry

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import com.jv23.echojournal.R
import com.jv23.echojournal.presentation.core.components.AudioSeekBar
import com.jv23.echojournal.domain.data_source.model.MoodType
import com.jv23.echojournal.presentation.core.components.AppTopAppBar
import com.jv23.echojournal.presentation.core.components.TopicFilterDropdown
import com.jv23.echojournal.presentation.core.utils.ObserveAsEvents
import com.jv23.echojournal.presentation.core.utils.getResIdByMood
import com.jv23.echojournal.presentation.core.utils.showToastStr
import com.jv23.echojournal.presentation.screens.entry.components.MoodBottomSheet
import com.jv23.echojournal.presentation.screens.entry.components.TopicsAndInput
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryAction
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryEvent
import com.jv23.echojournal.presentation.screens.entry.handling.NewEntryState
import com.jv23.echojournal.ui.theme.Add_Icon
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Hashtag_Icon
import com.jv23.echojournal.ui.theme.ModeEditOutline_Icon
import com.jv23.echojournal.ui.theme.OnPrimaryContainer
import com.jv23.echojournal.ui.theme.OutLineVariant
import com.jv23.echojournal.ui.theme.Outline
import com.jv23.echojournal.ui.theme.Primary
import com.jv23.echojournal.ui.theme.SurfaceVariant
import kotlinx.coroutines.launch

@Composable
fun NewEntryScreenRoot(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewEntryViewModel = viewModel<NewEntryViewModel>(
        factory = NewEntryViewModel.Factory)
    ) {

    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is NewEntryEvent.Error -> {
                context.showToastStr(event.error)
            }
            NewEntryEvent.NavigateBack -> {
                onNavigateBack
            }
            NewEntryEvent.Success -> {
                context.showToastStr("Journal successfully created")
                onNavigateBack
            }
        }
    }

    NewEntryScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction,


    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun NewEntryScreen(
    modifier: Modifier,
    state: NewEntryState,
    onAction: (NewEntryAction) -> Unit,

) {
    val color = Color(0xffc1c3ce)
    var topicsHeight by remember {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    fun hideBottomSheet() {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            onAction(NewEntryAction.OnToggleSelectMoodBottomSheet(false))
        }
    }

    if (state.isSelectMoodBottomSheetOpened) {
        ModalBottomSheet(
            onDismissRequest = ::hideBottomSheet,
            sheetState = sheetState,
            dragHandle = null,
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            MoodBottomSheet(
                initialMood = state.selectedMood,
                onCancel = ::hideBottomSheet,
                onConfirm = {
                    onAction(NewEntryAction.OnSelectMood(it))
                    hideBottomSheet()
                }
            )
        }
    }

    Scaffold(
        topBar = {
            AppTopAppBar(
                title = stringResource(R.string.top_app_bar_new_entry),
                onBackClick = {}
            )
        }
    ) { contentPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = modifier
                            .clickable { }
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.errorContainer)
                            .clickable {
                                onAction(NewEntryAction.OnToggleSelectMoodBottomSheet(true))
                            },
                        contentAlignment = Alignment.Center

                    ) {
                        if (state.selectedMood == null) {
                            Icon(
                                imageVector = Add_Icon,
                                contentDescription = stringResource(R.string.close_icon),
                                modifier = modifier
                                    .size(20.dp),
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                        } else {
                            Image(
                                painter = painterResource(getResIdByMood(state.selectedMood)),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }


                    }
                    Spacer(modifier = modifier.width(12.dp))
                    BasicTextField(
                        value = state.title,
                        onValueChange = {
                            onAction(NewEntryAction.OnTitleChange(it))
                        },
                        textStyle = MaterialTheme.typography.headlineLarge,
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                if (state.title.isBlank()) {
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

                //Topic
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .onGloballyPositioned {
                                    topicsHeight = it.size.height
                                },
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "#",
                                color = color,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                            TopicsAndInput(
                                color = color,
                                topics = state.selectedTopics,
                                input = state.inputTopic,
                                onInputChange = {
                                    onAction(NewEntryAction.OnInputTopic(it))
                                },
                                onDelete = {
                                    onAction(NewEntryAction.OnDeleteTopic(it))
                                },
                                onFocusChange = {
                                    onAction(NewEntryAction.OnTopicFieldFocusChange(it))
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        DescriptionTextField(
                            description = state.description,
                            onValueChange = {
                                onAction(NewEntryAction.OnDescriptionChange(it))
                            },
                            tint = color,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 16.dp)
                        )
                    }
                    TopicFilterDropdown(
                        query = state.inputTopic,
                        filteredTopics = state.unselectedTopics,
                        onTopicClick = {
                            onAction(NewEntryAction.OnSelectTopic(it))
                        },
                        isNewTopic = state.isNewTopic,
                        onCreateNewTopicClick = {
                            onAction(NewEntryAction.OnAddNewTopic)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset {
                                IntOffset(0, topicsHeight + 8)
                            }
                            .padding(horizontal = 16.dp)
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
                    )
                }
                CancelAndSaveButton(
                    canSave = state.canSave,
                    onCancelClick = {
                        onAction(NewEntryAction.OnToggleCancelDialog(true))
                    },
                    onSaveClick = {
                        onAction(NewEntryAction.OnSaveClick)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                )


            }
        }
    }
}

@Composable
private fun DescriptionTextField(
    modifier: Modifier = Modifier,
    description: String,
    onValueChange: (String) -> Unit,
    tint: Color
) {
    Row (
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = tint
        )
        Spacer(modifier = Modifier.width(6.dp))
        BasicTextField(
            value = description,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.bodyMedium,
            decorationBox = { innerBox ->
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    if (description.isBlank()) {
                        Text(
                            text = stringResource(R.string.add_description),
                            color = tint,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerBox()
                }
            },
            modifier = Modifier.weight(1f)
        )

    }

}

@Composable
private fun CancelAndSaveButton(
    canSave: Boolean,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onCancelClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Cancel"
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onSaveClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f),
            enabled = canSave
        ) {
            Text(
                text = "Save"
            )
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
            onAction = {},

        )

    }
}