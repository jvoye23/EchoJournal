package com.jv23.echojournal.navigation

import kotlinx.serialization.Serializable

object AppNavigationGraph {

    @Serializable
    object HomeScreen

    @Serializable
    data class NewEntryScreen(
        val id: Long,
        val fileUri: String
    )
}