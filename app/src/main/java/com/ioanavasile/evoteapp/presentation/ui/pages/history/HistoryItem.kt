package com.ioanavasile.evoteapp.presentation.ui.pages.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ioanavasile.evoteapp.domain.model.VoteHistory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryItem(history: VoteHistory) {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = Date(history.timestamp)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        Text(text = history.electionName, fontSize = 18.sp)
        Text(text = history.candidateName, fontSize = 12.sp)
        Text(text = history.candidateParty, fontSize = 12.sp)
        Text(text = dateFormat.format(date), fontSize = 12.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryItemPreview() {
    HistoryItem(
        history = VoteHistory(
            "Election 1",
            "Candidate A",
            "Party 1",
            System.currentTimeMillis()
        )
    )
}