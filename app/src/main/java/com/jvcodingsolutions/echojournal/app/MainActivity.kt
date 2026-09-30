package com.jvcodingsolutions.echojournal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jvcodingsolutions.echojournal.app.navigation.NavigationRoot
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.theme.EchoJournalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EchoJournalTheme {
                NavigationRoot()
            }
        }
    }
}