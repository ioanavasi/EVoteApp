package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ioanavasile.evoteapp.R

data class MenuItem(val text: String, val onClick: () -> Unit)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTopBar(
    onLogout: () -> Unit,
    onVote: () -> Unit,
    onHistory: () -> Unit
) {
    val menuItems = listOf(
        MenuItem("Vote", onVote),
        MenuItem("History", onHistory),
        MenuItem("Logout", onLogout)
    )

    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    Icons.Filled.Menu,
                    stringResource(R.string.menu_content_description)
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                menuItems.forEach { menuItem ->
                    DropdownMenuItem(
                        text = { Text(menuItem.text) },
                        onClick = {
                            showMenu = false
                            menuItem.onClick()
                        }
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}