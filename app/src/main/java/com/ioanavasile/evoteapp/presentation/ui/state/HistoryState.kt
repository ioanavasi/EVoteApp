package com.ioanavasile.evoteapp.presentation.ui.state

import com.ioanavasile.evoteapp.domain.model.VoteHistory

sealed class HistoryState {
    data object Idle : HistoryState()
    data object Loading : HistoryState()
    data class Success(val history: List<VoteHistory>) : HistoryState()
    data class Error(val message: String) : HistoryState()
}
