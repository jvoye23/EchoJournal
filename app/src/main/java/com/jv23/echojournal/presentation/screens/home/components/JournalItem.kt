package com.jv23.echojournal.presentation.screens.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.domain.data_source.model.Mood
import com.jv23.echojournal.domain.entity.JournalEntryEntity
import com.jv23.echojournal.presentation.core.components.AudioPlayer
import com.jv23.echojournal.presentation.core.components.Topic
import com.jv23.echojournal.presentation.core.utils.formatLocalDateTimeToHourMinute
import com.jv23.echojournal.presentation.core.utils.getMoodColors
import com.jv23.echojournal.presentation.core.utils.getResIdByMood
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import java.time.LocalDateTime
import kotlin.random.Random


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun JournalItem(
    item: JournalEntryEntity,
    index: Int,
    isLastItem: Boolean,
    isFileInPlayback: Boolean,
    isPlaying: Boolean,
    curPlaybackInSeconds: Long,
    onTopicClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    onTogglePlayback: () -> Unit = {},
    onSeekPlayback: (Int) -> Unit = {}
) {
    var height by remember {
        mutableIntStateOf(0)
    }
    val density = LocalDensity.current

    var expandable by remember {
        mutableStateOf(false)
    }
    var showMore by remember {
        mutableStateOf(false)
    }
    var description by remember {
        mutableStateOf(item.description)
    }

    LaunchedEffect(showMore) {
        if (showMore) {
            description = item.description
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (index != 0) {
                VerticalDivider(modifier = Modifier.height(8.dp))
            } else {
                Spacer(modifier = Modifier.height(8.dp))
            }

            Image(
                painter = painterResource(id = getResIdByMood(item.mood)),
                contentDescription = null
            )
            if (!isLastItem) {
                VerticalDivider(
                    modifier = Modifier.height(with(density) { height.toDp() })
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(
                    vertical = 12.dp,
                    horizontal = 14.dp
                )
                .onGloballyPositioned {
                    height = it.size.height
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = formatLocalDateTimeToHourMinute(item.dateTimeCreated),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(6.dp))

            AudioPlayer(
                isPlaying = isPlaying && isFileInPlayback,
                curPlaybackInSeconds = curPlaybackInSeconds,
                maxPlaybackInSeconds = item.maxPlaybackInSeconds,
                moodColors = getMoodColors(item.mood),
                onToggle = { onTogglePlayback() },
                onValueChange = onSeekPlayback,
                enableSlider = isFileInPlayback,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))

            val isClickable = expandable && !showMore
            val annotatedText = getDescription(description, isClickable)
            Text(
                text = annotatedText,
                maxLines = if (showMore) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
                    .clickable(
                        enabled = isClickable
                    ) {
                        showMore = true
                    }
                    .semantics {
                        this.contentDescription = item.description
                    },
                color = MaterialTheme.colorScheme.surfaceVariant,
                onTextLayout = {
                    if (!showMore && it.hasVisualOverflow) {
                        expandable = true
                        description = item
                            .description
                            .substring(0, it.getLineEnd(2, visibleEnd = true))
                    }
                }
            )

            if (item.topics.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    item.topics.forEach { topic ->
                        Topic(
                            text = topic,
                            onClick = { onTopicClick(topic) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun getDescription(value: String, expandable: Boolean): AnnotatedString {
    val showMoreStr = "... Show more"
    return buildAnnotatedString {
        if (expandable && value.length > showMoreStr.length) {
            append(value.substring(0, value.length - showMoreStr.length))
        } else {
            append(value)
        }
        if (expandable) {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(showMoreStr)
            }
        }
    }
}

@Preview
@Composable
private fun JournalItemPreview() {
    EchoJournalTheme {
        JournalItem(
            item = dummyJournal(),
            index = 0,
            isLastItem = false,
            isFileInPlayback = false,
            isPlaying = false,
            curPlaybackInSeconds = 0,
            onTopicClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

private fun dummyJournal(
    dateTime: LocalDateTime? = null,
    topics: Set<String> = emptySet(),
    wordCount: Int = 69
): JournalEntryEntity {
    val randomDays = Random.nextInt(1, 8).toLong()

    return JournalEntryEntity(
        mood = Mood.entries.random(),
        title = "My Entry",
        description = LoremIpsum(wordCount).values.joinToString(" "),
        recordingUri = "",
        maxPlaybackInSeconds = 0,
        dateTimeCreated = dateTime ?: LocalDateTime.now().plusDays(-randomDays),
        topics = topics
    )
}