package com.ioanavasile.evoteapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    @Json(name = "cnp") val cnp: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "first_name")  val firstName: String,
    @Json(name = "last_name") val lastName: String,
)
