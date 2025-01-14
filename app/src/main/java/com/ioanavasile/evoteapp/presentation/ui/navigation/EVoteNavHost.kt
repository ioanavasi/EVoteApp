package com.ioanavasile.evoteapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ioanavasile.evoteapp.presentation.ui.HomeScreen
import com.ioanavasile.evoteapp.presentation.ui.authentication.LoginScreen
import com.ioanavasile.evoteapp.presentation.ui.authentication.RegisterScreen
import com.ioanavasile.evoteapp.presentation.viewModels.AuthViewModel

@Composable
fun EVoteNavHost() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(authViewModel, navController)
        }
        composable("register") {
            RegisterScreen(authViewModel, navController)
        }
        composable("home") {
            HomeScreen(authViewModel, navController)
        }
    }
}