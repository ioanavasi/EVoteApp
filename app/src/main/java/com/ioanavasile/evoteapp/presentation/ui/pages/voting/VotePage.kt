package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ioanavasile.evoteapp.domain.model.Candidate
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
            LazyColumn {
                items(elections) { election ->
                    ElectionItem(election, {
                        voteViewModel.getCandidates(election)
                    })
                }
            }
        }

        is VoteState.CandidateList -> {
            val currentState = voteState as VoteState.CandidateList
            val candidates = currentState.candidates
            val voted = currentState.voted

            val chosenCandidate = remember { mutableStateOf<Candidate?>(null) }
            val showConfirmDialog = remember { mutableStateOf(false) }
            val showConfirmDialogSanityCheck = remember { mutableStateOf(false) }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(candidates.chunked(2)) { rowCandidates ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (candidate in rowCandidates) {
                            CandidateItem(
                                candidate = candidate,
                                onCandidateClick = {
                                    if (!voted) {
                                        chosenCandidate.value = candidate
                                        showConfirmDialog.value = true
                                    }
                                }
                            )
                        }
                    }
                }
            }

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
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text("Vote registered!")
                Button(
                    onClick = { voteViewModel.resetVoteState() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Continue")
                }
            }
        }

        is VoteState.Loading -> {
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

        is VoteState.Error -> {
            val errorMessage = (voteState as VoteState.Error).message
            Text("Error: $errorMessage", color = Color.Red)
        }
    }
}