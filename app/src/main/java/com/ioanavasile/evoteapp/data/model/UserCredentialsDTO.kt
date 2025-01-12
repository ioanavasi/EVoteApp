package com.ioanavasile.evoteapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserCredentialsDTO(
    @Json(name = "cnp") val cnp: String,
    @Json(name = "password") val password: String,
)
