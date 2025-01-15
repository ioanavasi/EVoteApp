package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.domain.model.Candidate
import com.ioanavasile.evoteapp.presentation.ui.pages.ErrorPrompt
import com.ioanavasile.evoteapp.presentation.ui.pages.LoadingAnimation
import com.ioanavasile.evoteapp.presentation.ui.state.VoteState
import com.ioanavasile.evoteapp.presentation.viewModels.AuthViewModel
import com.ioanavasile.evoteapp.presentation.viewModels.VoteViewModel

@Composable
fun VotePage(
    authViewModel: AuthViewModel,
    voteViewModel: VoteViewModel,
    navController: NavController
) {
    val voteState by voteViewModel.voteState.collectAsState()

    when (voteState) {
        is VoteState.Idle -> {
            voteViewModel.getElections()
        }

        is VoteState.ElectionList -> {
            val elections = (voteState as VoteState.ElectionList).elections
            ElectionItemList(
                elections = elections,
                onLogout = {
                    authViewModel.logout()
                    authViewModel.resetLoginState()
                    navController.navigate("login")
                },
                onVote = {
                    voteViewModel.resetVoteState()
                    navController.navigate("vote")
                },
                onHistory = {
                    navController.navigate("history")
                },
                onElectionClick = { election ->
                    voteViewModel.getCandidates(election)
                },
            )
        }

        is VoteState.CandidateList -> {
            val currentState = voteState as VoteState.CandidateList
            val candidates = currentState.candidates
            val voted = currentState.voted

            val chosenCandidate = remember { mutableStateOf<Candidate?>(null) }
            val showConfirmDialog = remember { mutableStateOf(false) }
            val showConfirmDialogSanityCheck = remember { mutableStateOf(false) }

            CandidateItemGrid(
                candidates = candidates,
                onLogout = {
                    authViewModel.logout()
                    authViewModel.resetLoginState()
                    navController.navigate("login")
                },
                onVote = {
                    voteViewModel.resetVoteState()
                    navController.navigate("vote")
                },
                onHistory = {
                    navController.navigate("history")
                },
                onCandidateClick = { candidate ->
                    if (!voted) {
                        chosenCandidate.value = candidate
                        showConfirmDialog.value = true
                    }
                }
            )

            if (showConfirmDialog.value) {
                ConfirmVoteDialog(
                    message = "Are you sure you want to vote for ${chosenCandidate.value?.name}?",
                    onDismissRequest = {
                        showConfirmDialog.value = false
                   },
                    onSubmit = {
                        showConfirmDialog.value = false
                        showConfirmDialogSanityCheck.value = true
                    }
                )
            }

            if (showConfirmDialogSanityCheck.value) {
                ConfirmVoteDialog(
                    message = "Are you absolutely sure you want to vote for ${chosenCandidate.value?.name}?",
                    onDismissRequest = {
                        showConfirmDialogSanityCheck.value = false
                    },
                    onSubmit = {
                        showConfirmDialogSanityCheck.value = false
                        voteViewModel.vote(currentState.election, chosenCandidate.value!!.id)
                    }
                )
            }
        }

        is VoteState.VoteSuccess -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Text("Vote registered!")
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { voteViewModel.resetVoteState() },
                    ) {
                        Text("Proceed")
                    }
                }
            }
        }

        is VoteState.Loading -> {
            LoadingAnimation()
        }

        is VoteState.Error -> {
            val errorMessage = (voteState as VoteState.Error).message
            ErrorPrompt(
                onConfirm = { voteViewModel.resetVoteState() },
                title = "Voting Error",
                message = errorMessage
            )
        }
    }
}