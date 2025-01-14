package com.ioanavasile.evoteapp.domain.model

data class VoteHistory(
    val electionName: String,
    val candidateName: String,
    val candidateParty: String,
    val timestamp: Long
)
