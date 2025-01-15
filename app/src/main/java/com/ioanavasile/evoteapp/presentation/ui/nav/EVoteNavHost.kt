package com.ioanavasile.evoteapp.presentation.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ioanavasile.evoteapp.presentation.ui.pages.authentication.LoginPage
import com.ioanavasile.evoteapp.presentation.ui.pages.authentication.RegisterPage
import com.ioanavasile.evoteapp.presentation.ui.pages.history.HistoryPage
import com.ioanavasile.evoteapp.presentation.ui.pages.voting.VotePage
import com.ioanavasile.evoteapp.presentation.viewModels.AuthViewModel
import com.ioanavasile.evoteapp.presentation.viewModels.HistoryViewModel
import com.ioanavasile.evoteapp.presentation.viewModels.VoteViewModel

@Composable
fun EVoteNavHost() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(authViewModel, navController)
        }
        composable("register") {
            RegisterPage(authViewModel, navController)
        }
        composable("vote") {
            val voteViewModel: VoteViewModel = hiltViewModel()
            VotePage(authViewModel, voteViewModel, navController)
        }
        composable("history") {
            val historyViewModel: HistoryViewModel = hiltViewModel()
            HistoryPage(authViewModel, historyViewModel, navController)
        }
    }
}