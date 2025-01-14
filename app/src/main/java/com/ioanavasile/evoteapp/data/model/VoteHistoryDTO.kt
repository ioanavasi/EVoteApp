package com.ioanavasile.evoteapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoteHistoryDTO(
    @Json(name = "election_name") val electionName: String,
    @Json(name = "candidate_name") val candidateName: String,
    @Json(name = "candidate_party") val candidateParty: String,
    @Json(name = "timestamp") val timestamp: Long
)
