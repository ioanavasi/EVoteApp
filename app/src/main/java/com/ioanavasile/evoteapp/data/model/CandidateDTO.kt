package com.ioanavasile.evoteapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CandidateDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "election_id") val electionId: Int,
    @Json(name = "name") val name: String,
    @Json(name = "party") val party: String,
    @Json(name = "slogan") val slogan: String
)
