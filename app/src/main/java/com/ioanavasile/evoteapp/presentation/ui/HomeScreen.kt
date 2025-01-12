package com.ioanavasile.evoteapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.presentation.viewModels.LoginViewModel

@Composable
fun HomeScreen(viewModel: LoginViewModel, navController: NavController) {
    Column {
        Spacer(modifier = Modifier.height(100.dp))
        Text("Welcome!")
        TextButton(
            onClick = {
                viewModel.logout()
                navController.navigate("login") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}