package com.jv23.echojournal.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.domain.data_source.model.MoodType
import com.jv23.echojournal.presentation.core.components.AudioSeekBar
import com.jv23.echojournal.ui.theme.EchoJournalTheme

@Composable
fun EntryCard(
    modifier: Modifier = Modifier,
    moodType: MoodType,
    title: String,
    timeOfRecord: String,
    audioLogDescription: String?,
    categories: List<String>
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        // MoodIconColum
        Column(
            modifier = modifier
                .padding(top = 6.dp)
                .padding(end = 12.dp)


        ) {
            val moodIcon: Painter = painterResource(moodType.moodIcon)
            Image(
                painter = moodIcon ,
                contentDescription = null,
                modifier = Modifier
                    .width(32.dp)
                    .height(48.dp)
            )


        }
        //AudioEntryColumn
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            ElevatedCard(
                modifier = Modifier
                    .width(336.dp)
                    .padding(8.dp)
                    ,

                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)

            )  {
                // 1st Row
                Row(
                    modifier = Modifier
                        .width(308.dp)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically


                ) {
                    Text(
                        text = title,
                        modifier = Modifier,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = "17:30",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        modifier = Modifier

                    )


                }
                // 2nd Row
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 2.dp,
                            start = 10.dp,
                            top = 6.dp,
                            end = 10.dp
                        )

                ) {
                    AudioSeekBar(
                        moodType = moodType,
                        modifier = Modifier

                    )


                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 6.dp,
                            start = 10.dp,
                            top = 2.dp,
                            end = 10.dp
                        )

                ) {
                    if (audioLogDescription != null) {
                        Text(
                            text = audioLogDescription,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 12.dp,
                            start = 10.dp,

                            end = 10.dp
                        )

                ) {
                    TopicTag(
                        modifier = Modifier,
                        topic = "Work"
                    )
                }

            }
        }
    }


}

@Preview
@Composable
private fun EntryCardPreview(){
    EchoJournalTheme {

        val categories = listOf("Work", "Hobby", "Finance", "Home")

       EntryCard(
           modifier = Modifier,
           moodType = MoodType.ExcitedMood,
           title = "My Entry",
           timeOfRecord = "17:30",
           audioLogDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tit amet, consectetur adipiscing elit, sed t... Show more",
           categories = categories
       )
    }
}