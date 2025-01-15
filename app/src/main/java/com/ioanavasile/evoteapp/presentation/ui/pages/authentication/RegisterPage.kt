package com.ioanavasile.evoteapp.presentation.ui.pages.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.presentation.ui.pages.utility.ErrorPrompt
import com.ioanavasile.evoteapp.presentation.ui.pages.utility.LoadingAnimation
import com.ioanavasile.evoteapp.presentation.ui.state.AuthState
import com.ioanavasile.evoteapp.presentation.viewModels.AuthViewModel

@Composable
fun RegisterPage(viewModel: AuthViewModel, navController: NavController) {

    val registerState by viewModel.registerState.collectAsState()

    when (registerState) {
        is AuthState.Idle -> {
            RegisterForm(
                navController = navController,
                onRegisterClicked = { cnp, email, firstName, lastName, password ->
                    viewModel.register(cnp, email, firstName, lastName, password)
                },
            )
        }

        is AuthState.Loading -> {
            LoadingAnimation()
        }

        is AuthState.Success -> {
            navController.navigate("login")
        }

        is AuthState.Error -> {
            val errorMessage = (registerState as AuthState.Error).message
            ErrorPrompt(
                onConfirm = { viewModel.resetRegisterState() },
                title = "Register Error",
                message = errorMessage
            )
        }
    }
}