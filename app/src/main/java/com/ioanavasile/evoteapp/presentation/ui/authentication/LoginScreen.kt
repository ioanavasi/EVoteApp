package com.ioanavasile.evoteapp.presentation.ui.authentication

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
import com.ioanavasile.evoteapp.presentation.ui.state.LoginState
import com.ioanavasile.evoteapp.presentation.viewModels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is LoginState.Idle -> {
            LoginForm(
                navController = navController,
                onLoginClicked = { cnp, password -> viewModel.login(cnp, password) },
            )
        }
        is LoginState.Loading -> {
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
        is LoginState.Success -> {
            navController.navigate("home")
        }
        is LoginState.Error -> {
            val errorMessage = (loginState as LoginState.Error).message
            Text("Error: $errorMessage", color = Color.Red)
        }
    }
}