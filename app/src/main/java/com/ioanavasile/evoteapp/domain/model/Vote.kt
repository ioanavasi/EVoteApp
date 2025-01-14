package com.ioanavasile.evoteapp.domain.model

data class Vote(
    val electionId: Int,
    val candidateId: Int,
    val timestamp: Long
)
