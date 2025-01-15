package com.ioanavasile.evoteapp.presentation.ui.pages.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.presentation.ui.pages.ErrorPrompt
import com.ioanavasile.evoteapp.presentation.ui.pages.LoadingAnimation
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
            LoadingAnimation()
        }
        is AuthState.Success -> {
            navController.navigate("vote")
        }
        is AuthState.Error -> {
            val errorMessage = (loginState as AuthState.Error).message
            ErrorPrompt(
                onConfirm = { viewModel.resetLoginState() },
                title = "Login Error",
                message = errorMessage
            )
        }
    }
}