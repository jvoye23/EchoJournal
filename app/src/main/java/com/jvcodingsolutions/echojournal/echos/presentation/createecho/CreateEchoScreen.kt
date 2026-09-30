package com.jvcodingsolutions.echojournal.echos.presentation.createecho

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.theme.EchoJournalTheme

@Composable
fun CreateEchoScreenRoot(
    modifier: Modifier = Modifier
) {
    CreateEchoScreen()
}

@Composable
fun CreateEchoScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Create Echo Screen"
        )
    }

}

@Preview
@Composable
private fun CreateEchoScreenPreview() {
    EchoJournalTheme {
        CreateEchoScreen()
    }

}