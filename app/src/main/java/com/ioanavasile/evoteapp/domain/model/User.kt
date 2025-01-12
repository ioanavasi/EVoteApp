package com.ioanavasile.evoteapp.domain.model

data class User(
    val cnp: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
)
