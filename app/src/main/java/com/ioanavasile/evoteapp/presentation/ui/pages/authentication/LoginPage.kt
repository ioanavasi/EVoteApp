package com.ioanavasile.evoteapp.presentation.ui.pages.authentication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.presentation.ui.state.AuthState
import com.ioanavasile.evoteapp.presentation.viewModels.AuthViewModel

@Composable
fun LoginPage(viewModel: AuthViewModel, navController: NavController) {
    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is AuthState.Idle -> {
            LoginForm(
                navController = navController,
                onLoginClicked = { cnp, password -> viewModel.login(cnp, password) },
            )
        }
        is AuthState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                )
            }
        }
        is AuthState.Success -> {
            navController.navigate("vote")
        }
        is AuthState.Error -> {
            val errorMessage = (loginState as AuthState.Error).message
            Text("Error: $errorMessage", color = Color.Red)
        }
    }
}