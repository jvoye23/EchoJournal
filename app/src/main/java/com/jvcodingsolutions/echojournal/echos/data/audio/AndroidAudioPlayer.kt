package com.jvcodingsolutions.echojournal.echos.data.audio

import android.media.MediaPlayer
import com.jvcodingsolutions.echojournal.echos.domain.audio.AudioPlayer
import com.jvcodingsolutions.echojournal.echos.domain.audio.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.io.FileInputStream
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class AndroidAudioPlayer(
    private val applicationScope: CoroutineScope
): AudioPlayer {

    private val _activeTrack = MutableStateFlow<AudioTrack?>(null)
    override val activeTrack = _activeTrack.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    private var durationJob: Job? = null

    override fun play(filePath: String, onComplete: () -> Unit) {
        stop()

        // since the internal storage of the user is private we cannot use
        // Mediaplayer.setDataSource(filePath) to play the audio file directly, because the
        // EchoJournal App has no access to the internal storage of the user. Therefore we can use
        // a file descriptor to play the audio file
        mediaPlayer = MediaPlayer().apply {
            val fileInputStream = FileInputStream(File(filePath))
            try {
                setDataSource(fileInputStream.fd)

                prepare()
                start()

                _activeTrack.update {
                    AudioTrack(
                        totalDuration = this.duration.milliseconds,
                        durationPlayed = Duration.ZERO,
                        isPlaying = true
                    )
                }
                trackDuration()

                setOnCompletionListener {
                    onComplete()
                    stop()
                }
            } catch(e: Exception) {
                Timber.e(e, "Error playing audio")
            } finally {
                fileInputStream.close()
            }
        }
    }

    override fun pause() {
        if(activeTrack.value?.isPlaying != true) {
            return
        }
        _activeTrack.update { it?.copy(
            isPlaying = false
        ) }
        durationJob?.cancel()
        mediaPlayer?.pause()
    }

    override fun resume() {
        if(activeTrack.value?.isPlaying != false) {
            return
        }
        _activeTrack.update { it?.copy(
            isPlaying = true
        ) }
        mediaPlayer?.start()
        trackDuration()
    }

    override fun stop() {
        _activeTrack.update { it?.copy(
            isPlaying = false,
            durationPlayed = Duration.ZERO
        ) }
        durationJob?.cancel()
        mediaPlayer?.apply {
            stop()
            reset()
            release()
        }
        mediaPlayer = null
    }

    // Using a do while loop here to avoid race conditions where the while loop never enters
    private fun trackDuration() {
        durationJob?.cancel()
        durationJob = applicationScope.launch {
            do {
                _activeTrack.update { it?.copy(
                    durationPlayed = mediaPlayer?.currentPosition?.milliseconds ?: Duration.ZERO
                ) }
                delay(10L.milliseconds)
            } while(activeTrack.value?.isPlaying == true && mediaPlayer?.isPlaying == true)
        }
    }
}