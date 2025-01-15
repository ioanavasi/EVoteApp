package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ioanavasile.evoteapp.domain.model.Candidate
import com.ioanavasile.evoteapp.presentation.ui.pages.utility.SimpleTopBar

@Composable
fun CandidateItemGrid(
    candidates: List<Candidate>,
    onLogout: () -> Unit,
    onVote: () -> Unit,
    onHistory: () -> Unit,
    onCandidateClick: (Candidate) -> Unit
) {
    Scaffold(
        topBar = { SimpleTopBar (
            onLogout = onLogout,
            onVote = onVote,
            onHistory = onHistory
        ) }
    ) { contentPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(contentPadding)) {
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
                                onCandidateClick = { onCandidateClick(candidate) }
                            )
                        }
                    }
                }
            }
        }
    }
}