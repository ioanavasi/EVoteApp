package com.ioanavasile.evoteapp.presentation.ui.state

import com.ioanavasile.evoteapp.domain.model.Candidate
import com.ioanavasile.evoteapp.domain.model.Election

sealed class VoteState {
    data class ElectionList(val elections: List<Election>) : VoteState()

    data class CandidateList(
        val election: Election,
        val voted: Boolean,
        val candidates: List<Candidate>
    ) : VoteState()

    data class VoteSuccess(val election: Election) : VoteState()

    data object Loading : VoteState()
    data object Idle : VoteState()

    data class Error(val message: String) : VoteState()
}