package com.jv23.echojournal.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.jv23.echojournal.ui.theme.EchoJournalTheme
import com.jv23.echojournal.ui.theme.Gray6
import com.jv23.echojournal.ui.theme.Inter

@Composable
fun TopicTag(
    modifier: Modifier = Modifier,
    topic: String,
){
    Box (
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))


    ) {
        Row (
            modifier = Modifier
                .background(Gray6)
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
                
        ){
            Text(
                text = "#",
                fontFamily = Inter,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 18.sp,

                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(start = 6.dp)

            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = topic,
                fontFamily = Inter,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 18.sp,

                color = Color.Black,
                modifier = Modifier
                    .padding(start = 4.dp, end = 6.dp)

            )

        }

    }
}


@Composable
@Preview
private fun TopicTagPreview() {
    EchoJournalTheme {
        TopicTag(  modifier = Modifier, topic = "Work")

    }
}

