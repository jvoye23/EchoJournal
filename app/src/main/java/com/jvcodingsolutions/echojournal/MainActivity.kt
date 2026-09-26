package com.jvcodingsolutions.echojournal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.buttons.PrimaryButton
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.theme.bgGradient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EchoJournalTheme {
                Scaffold(
                    containerColor = Color.Transparent,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.bgGradient)
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        PrimaryButton(
                            text = "Primary Button",
                            onClick = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                )
                            }
                        )
                    }

                }
            }
        }
    }
}