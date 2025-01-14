package com.ioanavasile.evoteapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoteDTO(
    @Json(name = "election_id") val electionId: Int,
    @Json(name = "candidate_id") val candidateId: Int,
    @Json(name = "timestamp") val timestamp: Long
)
