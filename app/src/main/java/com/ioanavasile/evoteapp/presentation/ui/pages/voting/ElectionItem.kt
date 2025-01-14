package com.ioanavasile.evoteapp.presentation.ui.pages.voting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ioanavasile.evoteapp.domain.model.Election

@Composable
fun ElectionItem(election: Election, onElectionClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onElectionClick(election.id) }
        .padding(16.dp)
    ) {
        Text(text = election.name, fontSize = 18.sp)
    }
}