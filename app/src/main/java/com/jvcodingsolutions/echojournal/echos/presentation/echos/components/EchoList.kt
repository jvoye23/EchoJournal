package com.jvcodingsolutions.echojournal.echos.presentation.echos.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import com.jvcodingsolutions.echojournal.core.presentation.util.UiText
import com.jvcodingsolutions.echojournal.echos.presentation.echos.models.EchoDaySection
import com.jvcodingsolutions.echojournal.echos.presentation.echos.models.RelativePosition
import com.jvcodingsolutions.echojournal.echos.presentation.echos.models.TrackSizeInfo
import com.jvcodingsolutions.echojournal.echos.presentation.preview.PreviewModels
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlin.time.Clock


@Composable
fun EchoList(
    sections: List<EchoDaySection>,
    onPlayClick: (echoId: Int) -> Unit,
    onPauseClick: () -> Unit,
    onTrackSizeAvailable: (TrackSizeInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp)
    ) {
        sections.forEachIndexed { sectionIndex, (dateHeader, echos) ->
            stickyHeader {
                if(sectionIndex > 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Text(
                    text = dateHeader.asString().uppercase(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            itemsIndexed(
                items = echos,
                key = { _, echo -> echo.id }
            ) { index, echo ->
                EchoTimelineItem(
                    echoUi = echo,
                    relativePosition = when {
                        index == 0 && echos.size == 1 -> RelativePosition.SINGLE_ENTRY
                        index == 0 -> RelativePosition.FIRST
                        echos.lastIndex == index -> RelativePosition.LAST
                        else -> RelativePosition.IN_BETWEEN
                    },
                    onPlayClick = { onPlayClick(echo.id) },
                    onPauseClick = onPauseClick,
                    onTrackSizeAvailable = onTrackSizeAvailable
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun EchoListPreview() {
    EchoJournalTheme {
        val todayEchos = remember {
            (1..3).map {
                PreviewModels.echoUi.copy(
                    id = it,
                    recordedAt = Clock.System.now()
                )
            }
        }
        val yesterdaysEchos = remember {
            (4..6).map {
                PreviewModels.echoUi.copy(
                    id = it,
                    recordedAt = Clock.System.now()
                        .minus(
                            period = DateTimePeriod(days = 1),
                            timeZone = TimeZone.currentSystemDefault()
                        )
                )
            }
        }
        val echosFrom2DaysAgo = remember {
            (7..9).map {
                PreviewModels.echoUi.copy(
                    id = it,
                    recordedAt = Clock.System.now()
                        .minus(
                            period = DateTimePeriod(days = 2),
                            timeZone = TimeZone.currentSystemDefault()
                        )
                )
            }
        }
        val sections = remember {
            listOf(
                EchoDaySection(
                    dateHeader = UiText.Dynamic("Today"),
                    echos = todayEchos
                ),
                EchoDaySection(
                    dateHeader = UiText.Dynamic("Yesterday"),
                    echos = yesterdaysEchos
                ),
                EchoDaySection(
                    dateHeader = UiText.Dynamic("2025/04/25"),
                    echos = echosFrom2DaysAgo
                ),
            )
        }

        EchoList(
            sections = sections,
            onPauseClick = {},
            onPlayClick = {},
            onTrackSizeAvailable = {}
        )
    }
}