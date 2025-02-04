package com.jv23.echojournal.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jv23.echojournal.presentation.screens.home.EntriesScreenRoot
import com.jv23.echojournal.presentation.screens.home.EntriesViewModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    navigationState: NavigationState,
    application: Application,
    isDataLoaded: ()-> Unit,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navigationState.navHostController,
        startDestination = EntriesScreen

    ){
        composable<EntriesScreen> {
            EntriesScreenRoot(
                viewModel = EntriesViewModel(application),
                modifier = Modifier
            )
        }
        composable<NewEntryScreen> {

        }
    }
}

@Serializable
object EntriesScreen



@Serializable
object NewEntryScreen

