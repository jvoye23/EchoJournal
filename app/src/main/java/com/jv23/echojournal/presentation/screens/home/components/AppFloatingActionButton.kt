package com.jv23.echojournal.presentation.screens.home.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.R
import com.jv23.echojournal.ui.theme.Add_Icon
import com.jv23.echojournal.ui.theme.EchoJournalTheme

@Composable
fun AppFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .width(64.dp)
            .height(64.dp),
        shape = CircleShape,
        content = {
            Icon(
                imageVector = Add_Icon,
                contentDescription = stringResource(id = R.string.settings_icon),
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
                )
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        
        
    ) 
}

@Preview
@Composable
private fun AppFloatingActionButtonPreview() {
    EchoJournalTheme {
        AppFloatingActionButton(
            modifier = Modifier,
            onClick = {}
        )
    }

}