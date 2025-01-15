package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ioanavasile.evoteapp.domain.model.Election

@Composable
fun ElectionItemList(
    elections: List<Election>,
    onLogout: () -> Unit,
    onVote: () -> Unit,
    onHistory: () -> Unit,
    onElectionClick: (Election) -> Unit
) {
    Scaffold(
        topBar = { SimpleTopBar (
            onLogout = onLogout,
            onVote = onVote,
            onHistory = onHistory
        ) }
    ) { contentPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
            LazyColumn {
                items(elections) { election ->
                    ElectionItem(election, { onElectionClick(election) })
                }
            }
        }
    }
}