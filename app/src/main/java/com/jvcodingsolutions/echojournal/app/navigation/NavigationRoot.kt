package com.jvcodingsolutions.echojournal.app.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.jvcodingsolutions.echojournal.echos.presentation.create_echo.CreateEchoScreenRoot
import com.jvcodingsolutions.echojournal.echos.presentation.create_echo.CreateEchoViewModel
import com.jvcodingsolutions.echojournal.echos.presentation.echos.EchosScreenRoot
import com.jvcodingsolutions.echojournal.echos.presentation.util.toCreateEchoRoute
import com.jvcodingsolutions.echojournal.echos.presentation.util.toRecordingDetails
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationRoot() {

    val backStack = rememberNavBackStack(NavigationRoute.EchosNavKey)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = { key ->
            when (key) {
                is NavigationRoute.EchosNavKey -> NavEntry(key) {
                    EchosScreenRoot(
                        onNavigateToCreateEcho = { recordingDetails ->
                            backStack.add(recordingDetails.toCreateEchoRoute())
                        }
                    )
                }

                is NavigationRoute.CreateEchoNavKey -> NavEntry(key) {
                    val createEchoViewModel: CreateEchoViewModel =
                        koinViewModel {
                            parametersOf(key.toRecordingDetails())
                        }
                    CreateEchoScreenRoot(
                        onConfirmLeave = {
                            backStack.removeLastOrNull()
                        },
                        viewModel = createEchoViewModel,
                    )
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    )
}

