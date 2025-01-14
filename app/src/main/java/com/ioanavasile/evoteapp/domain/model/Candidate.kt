package com.ioanavasile.evoteapp.domain.model

data class Candidate(
    val id: Int,
    val electionId: Int,
    val name: String,
    val party: String,
    val slogan: String
)
