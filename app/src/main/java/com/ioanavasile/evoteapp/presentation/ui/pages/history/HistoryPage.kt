package com.ioanavasile.evoteapp.presentation.ui.pages.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.presentation.ui.pages.utility.ErrorPrompt
import com.ioanavasile.evoteapp.presentation.ui.pages.utility.LoadingAnimation
import com.ioanavasile.evoteapp.presentation.ui.state.HistoryState
import com.ioanavasile.evoteapp.presentation.viewModels.AuthViewModel
import com.ioanavasile.evoteapp.presentation.viewModels.HistoryViewModel

@Composable
fun HistoryPage(
    authViewModel: AuthViewModel,
    historyViewModel: HistoryViewModel,
    navController: NavController
) {
    val historyState by historyViewModel.historyState.collectAsState()

    when (historyState) {
        is HistoryState.Error -> {
            val errorMessage = (historyState as HistoryState.Error).message
            ErrorPrompt(
                onConfirm = { historyViewModel.resetHistoryState() },
                title = "History Error",
                message = errorMessage
            )
        }

        is HistoryState.Idle -> {
            historyViewModel.getVoteHistory()
        }

        is HistoryState.Loading -> {
            LoadingAnimation()
        }

        is HistoryState.Success -> {
            val historyList = (historyState as HistoryState.Success).history
            HistoryItemList(
                historyList = historyList,
                onLogout = {
                    authViewModel.logout()
                    authViewModel.resetLoginState()
                    navController.navigate("login")
                },
                onVote = {
                    historyViewModel.resetHistoryState()
                    navController.navigate("vote")
                },
                onHistory = {
                    historyViewModel.resetHistoryState()
                    navController.navigate("history")
                }
            )
        }
    }

}