package com.jv23.echojournal.presentation.core.components


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jv23.echojournal.R
import com.jv23.echojournal.ui.theme.NavigateBefore_Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    title: String = "",
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
        },
        navigationIcon = {
            IconButton(onClick = {  onBackClick }) {
                Icon(
                    imageVector = NavigateBefore_Icon,
                    contentDescription = stringResource(id = R.string.settings_icon),
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                )
            }
        }
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