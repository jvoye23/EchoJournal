package com.jv23.echojournal.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import com.jv23.echojournal.presentation.screens.entry.NewEntryScreenRoot
import com.jv23.echojournal.presentation.screens.home.EntriesScreenRoot
import kotlinx.serialization.Serializable


@Serializable
data class NewEntryRoute(
    val id: String,
    val fileUri: String
)

@Serializable
data object HomeRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            EntriesScreenRoot(
                onNavigateToNewEntryScreen = { id, fileUri ->
                    navController.navigate(NewEntryRoute(id, fileUri))
                }
            )
        }
        composable<NewEntryRoute> {
            val route = it.toRoute<NewEntryRoute>()
            Log.d("Route", "AppNavigation: ID = ${route.id}, FileURI = ${route.fileUri}")
            NewEntryScreenRoot(
                onNavigateBack = {navController.navigateUp()
                }
            )
        }
    }
}


@Composable
fun TestNavHost(
    modifier: Modifier = Modifier

){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TestHome
    ) {
        composable<TestHome> {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {navController.navigate(TestUser(
                        age = 20,
                        name = "Peter"
                    ))}
                ) {
                    Text(text = "Go to next screen")
                }
            }
        }
        composable<TestUser> {

            val args = it.toRoute<TestUser>()

            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "${args.name}, ${args.age} years old")
            }

        }
    }
}



@Serializable
object TestHome

@Serializable
data class TestUser(
    val age: Int,
    val name: String
)

