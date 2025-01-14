package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ioanavasile.evoteapp.domain.model.Candidate

@Composable
fun CandidateItem(
    candidate: Candidate,
    onCandidateClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onCandidateClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = candidate.name, fontWeight = FontWeight.Bold)
            Text(text = candidate.party)
            Text(text = candidate.slogan)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CandidateItemPreview() {
    val candidate = Candidate(0, 0, "John Doe", "Party A", "Slogan 1")
    CandidateItem(candidate)
}