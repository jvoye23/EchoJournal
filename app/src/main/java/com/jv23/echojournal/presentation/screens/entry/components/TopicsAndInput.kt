package com.jv23.echojournal.presentation.screens.entry.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jv23.echojournal.presentation.core.components.Topic
import com.jv23.echojournal.presentation.core.components.TopicTextField

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowScope.TopicsAndInput(
    color: Color,
    topics: Set<String>,
    input: String,
    onInputChange: (String) -> Unit,
    onDelete: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit,
) {
    topics.forEach {
        Topic(
            text = it,
            onClick = {},
            isDeletable = true,
            onDelete = {
                onDelete(it)
            }
        )
    }
    TopicTextField(
        value = input,
        onValueChange = {
            onInputChange(it)
        },
        hintColor = color,
        modifier = Modifier.weight(1f),
        onFocusChange = onFocusChange
    )
}