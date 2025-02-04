package com.jv23.echojournal.presentation.screens.entry.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jv23.echojournal.R
import com.jv23.echojournal.presentation.screens.entry.handling.EntriesAction
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
    modifier: Modifier = Modifier,
    onEvent: (EntriesAction) -> Unit,
    isMoodIconClicked: Boolean = false,

    ) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(293.dp)
            .padding(top = 24.dp,bottom = 24.dp)
    ) {
        Row(
            modifier = modifier
                .align(Alignment.TopCenter)
        ){
            Text(
                text = stringResource(id = R.string.how_are_you_doing),
                style = MaterialTheme.typography.headlineMedium,
                color = OnSurface
            )
        }
        Row(
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            var moodIconClicked: Boolean = false

            Box(
                modifier = modifier
                    .clickable {
                        //onEvent(EntriesAction.OnPlayClick)
                        moodIconClicked = true
                    }
                    .fillMaxHeight()
            ) {
                val visibleIcon: ImageVector = if (moodIconClicked){
                    StressedMood_Icon
                } else UnselectedStressedMood_Icon

                Image(
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.TopCenter),

                    imageVector = visibleIcon,
                    contentDescription = stringResource(id = R.string.stressed_icon),
                )
                Text(
                    modifier = modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.stressed),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                    )
            }
            Box(
                modifier = modifier
                    .clickable {onEvent(EntriesAction.OnPlayClick) }
                    .fillMaxHeight()
            ) {
                val visibleIcon: ImageVector = if (isMoodIconClicked){
                    SadMood_Icon
                } else UnselectedSadMood_Icon

                Image(
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.TopCenter),

                    imageVector = visibleIcon,
                    contentDescription = stringResource(id = R.string.sad_icon),
                )
                Text(
                    modifier = modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.sad),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                )
            }
            Box(
                modifier = modifier
                    .clickable {onEvent(EntriesAction.OnPlayClick) }
                    .fillMaxHeight()
            ) {
                val visibleIcon: ImageVector = if (isMoodIconClicked){
                    NeutralMood_Icon
                } else UnselectedNeutralMood_Icon

                Image(
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.TopCenter),

                    imageVector = visibleIcon,
                    contentDescription = stringResource(id = R.string.neutral_icon),
                )
                Text(
                    modifier = modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.neutral),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                )
            }
            Box(
                modifier = modifier
                    .clickable {onEvent(EntriesAction.OnPlayClick) }
                    .fillMaxHeight()
            ) {
                val visibleIcon: ImageVector = if (isMoodIconClicked){
                    PeacefulMood_Icon
                } else UnselectedPeacefulMood_Icon

                Image(
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.TopCenter),

                    imageVector = visibleIcon,
                    contentDescription = stringResource(id = R.string.peaceful_icon),
                )
                Text(
                    modifier = modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.peaceful),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                )
            }
            Box(
                modifier = modifier
                    .clickable {onEvent(EntriesAction.OnPlayClick) }
                    .fillMaxHeight()
            ) {
                val visibleIcon: ImageVector = if (isMoodIconClicked){
                    ExcitedMood_Icon
                } else UnselectedExcitedMood_Icon

                Image(
                    modifier = modifier
                        .size(40.dp)
                        .align(Alignment.TopCenter),

                    imageVector = visibleIcon,
                    contentDescription = stringResource(id = R.string.excited_icon),
                )
                Text(
                    modifier = modifier
                        .align(Alignment.BottomCenter),
                    text = stringResource(id = R.string.excited),
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurfaceVariant,
                )
            }



        }


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
                enabled = true,
                colors = ButtonColors(
                    containerColor = Primary,
                    contentColor = Color.White,
                    disabledContainerColor = SurfaceVariant,
                    disabledContentColor = Outline
                ),
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.check_button),
                    tint = Color.White,
                    modifier = modifier
                        .size(18.dp)

                )
                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = stringResource(R.string.confirm),
                    fontSize = 16.sp
                )
            }
        }

    }

}

@Preview()
@Composable
private fun MoodBottomSheetPreview(){
    EchoJournalTheme {
        MoodBottomSheet(
            modifier = Modifier,
            onEvent = {},
            isMoodIconClicked = false
        )
    }
}