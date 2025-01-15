package com.ioanavasile.evoteapp.presentation.ui.pages.utility

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorPrompt(
    onConfirm: () -> Unit,
    title: String = "Error",
    message: String = "An error has occurred"
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("OK")
            }
        }
    )
}