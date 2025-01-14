package com.ioanavasile.evoteapp.domain.repo

import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.Candidate

interface CandidateRepository {

    suspend fun getCandidates(electionId: Int): ApiResult<List<Candidate>>
}