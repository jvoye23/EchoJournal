package com.jv23.echojournal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlin.reflect.KClass

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String) {
        navHostController.navigate(route)
    }

    fun popBackStack() = navHostController.popBackStack()

    fun navigateToEntry(
        audioFilePath: String,
        entryId: Long = -1L
    ) {
        //navHostController.navigate()
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}