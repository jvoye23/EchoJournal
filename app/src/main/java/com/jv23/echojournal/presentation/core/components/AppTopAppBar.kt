package com.jv23.echojournal.presentation.core.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jv23.echojournal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar() {
    TopAppBar(
        title = { Text(
            text = stringResource(id = R.string.top_app_bar_title),
            style = MaterialTheme.typography.headlineLarge
        )
        },
        /*actions = {
            IconButton(onClick = { *//* Do something *//*}) {
                Icon(
                    imageVector = Settings_Icon,
                    contentDescription = stringResource(id = R.string.settings_icon),
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                )
            }
        }*/
    )
}