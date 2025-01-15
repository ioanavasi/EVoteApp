package com.ioanavasile.evoteapp.presentation.ui.pages.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ioanavasile.evoteapp.domain.model.VoteHistory
import com.ioanavasile.evoteapp.presentation.ui.pages.utility.SimpleTopBar

@Composable
fun HistoryItemList(
    historyList: List<VoteHistory>,
    onLogout: () -> Unit = {},
    onVote: () -> Unit = {},
    onHistory: () -> Unit = {}
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
                items(historyList) { history ->
                    HistoryItem(history)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemListPreview() {
    HistoryItemList(
        historyList = listOf(
            VoteHistory(
                "Election 1",
                "Candidate A",
                "Party 1",
                0
            ),
            VoteHistory(
                "Election 2",
                "Candidate B",
                "Party 2",
                0
            ),
            VoteHistory(
                "Election 3",
                "Candidate C",
                "Party 3",
                0
            ),
            VoteHistory(
                "Election 4",
                "Candidate D",
                "Party 4",
                0
            ),
            VoteHistory(
                "Election 5",
                "Candidate E",
                "Party 5",
                0
            )
        )
    )
}