package com.jv23.echojournal.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jv23.echojournal.presentation.screens.entry.NewEntryScreen
import com.jv23.echojournal.presentation.screens.entry.NewEntryScreenRoot
import com.jv23.echojournal.presentation.screens.home.EntriesScreenRoot
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier

) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavigationGraph.HomeScreen
    ) {
        composable<AppNavigationGraph.HomeScreen> {
            EntriesScreenRoot(
                onNavigateToNewEntryScreen = navController.navigate(AppNavigationGraph.NewEntryScreen(
                    id = 1L, fileUri = "filePath"
                ))
            )

        }
        composable<AppNavigationGraph.NewEntryScreen> {
            NewEntryScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                },
                modifier = Modifier

            )
        }
    }
}

private fun NavGraphBuilder.journalGraph(navController: NavHostController) {
    navigation<AppNavigationGraph>(startDestination = AppNavigationGraph.HomeScreen) {
        composable<AppNavigationGraph.HomeScreen> {
            EntriesScreenRoot(
                onNavigateToNewEntryScreen = navController.navigate(AppNavigationGraph.NewEntryScreen(
                    id = 1L, fileUri = "filePath"
                ))
            )

        }
        composable<AppNavigationGraph.NewEntryScreen> {
            NewEntryScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                },
                modifier = Modifier

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
object EntriesScreen



@Serializable
object NewEntryScreen

@Serializable
object TestHome

@Serializable
data class TestUser(
    val age: Int,
    val name: String
)

