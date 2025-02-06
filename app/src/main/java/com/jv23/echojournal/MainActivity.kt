package com.jv23.echojournal

import android.Manifest
import android.animation.ObjectAnimator
import android.app.Application
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jv23.echojournal.domain.audiorecorder.playback.AndroidAudioPlayer
import com.jv23.echojournal.domain.audiorecorder.record.AndroidAudioRecorder
import com.jv23.echojournal.presentation.core.components.AppTopAppBar
import com.jv23.echojournal.di.MyViewModel
import com.jv23.echojournal.navigation.AppNavigation
import com.jv23.echojournal.presentation.screens.entry.NewEntryViewModel
import com.jv23.echojournal.presentation.screens.home.EntriesViewModel
import com.jv23.echojournal.ui.theme.EchoJournalTheme
import java.io.File

class MainActivity : ComponentActivity() {

    private val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 123

    private val mainViewModel by viewModels<MainViewModel>()

    private lateinit var entriesViewModel: EntriesViewModel
    private lateinit var newEntryViewModel: NewEntryViewModel

    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        entriesViewModel = ViewModelProvider(this).get(
            EntriesViewModel::class.java
        )

        newEntryViewModel = ViewModelProvider(this).get(
            NewEntryViewModel::class.java
        )

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                !mainViewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.9f,
                    0.0f
                )

                zoomX.duration = 200L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.9f,
                    0.0f
                )

                zoomY.duration = 200L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()

            }
        }
        enableEdgeToEdge()

        setContent {
            EchoJournalTheme {

                val manualViewModel = viewModel<MyViewModel>(
                    factory = EchoJournalApplication.container.myViewModelFactory
                )



                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppTopAppBar()
                    requestRecordAudioPermission()
                   /* AppNavigation(
                        navigationState = navigationState,
                        isDataLoaded = { false },
                        modifier = Modifier
                            .padding(innerPadding),
                        application = Application()
                    )*/

                    //EntriesScreenRoot(viewModel = EntriesViewModel(application = application), modifier = Modifier.padding(innerPadding))

                    /*TestNavHost(
                        modifier = Modifier.padding((innerPadding))
                    )*/
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),

                    )

                }
            }
        }
    }

    private fun requestRecordAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted, proceed with audio recording
            Log.d("MainActivity", "RECORD_AUDIO permission already granted")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        when(requestCode){
            RECORD_AUDIO_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with audio recording
                    Log.d("MainActivity", "RECORD_AUDIO permission granted")
                } else {
                    // Permission denied, handle accordingly
                    Log.d("MainActivity", "RECORD_AUDIO permission denied")
                }
            }
        }
    }
}

