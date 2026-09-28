package com.jvcodingsolutions.echojournal.app

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.jvcodingsolutions.echojournal.core.presentation.designsystem.theme.EchoJournalTheme
import com.jvcodingsolutions.echojournal.echos.data.recording.AndroidVoiceRecorder
import com.jvcodingsolutions.echojournal.echos.presentation.echos.components.EchoExpandableText

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val recorder = AndroidVoiceRecorder(
            context = applicationContext,
            applicationScope = (application as EchoJournalApp).applicationScope

        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            EchoJournalTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button(
                        onClick = {
                            recorder.start()

                        }
                    ) {
                        Text(
                            text = "Start"
                        )
                    }

                    Button(
                        onClick = {
                            recorder.pause()
                        }
                    ) {
                        Text(
                            text = "Pause"
                        )
                    }

                    Button(
                        onClick = {
                            recorder.stop()
                        }
                    ) {
                        Text(
                            text = "Stop"
                        )
                    }


                }

            }
        }
    }
}