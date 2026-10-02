package com.jvcodingsolutions.echojournal.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface NavigationRoute {
    @Serializable
    data object EchosNavKey: NavKey

    @Serializable
    data class CreateEchoNavKey(
        val recordingPath: String,
        val duration: Long,
        val amplitudes: String,
    ): NavKey

    @Serializable
    data object SettingsNavKey: NavKey
}
