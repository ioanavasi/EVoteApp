package com.ioanavasile.evoteapp.data.repo

import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.model.CandidateDTO
import com.ioanavasile.evoteapp.domain.model.Candidate
import com.ioanavasile.evoteapp.domain.repo.CandidateRepository

class CandidateRepositoryImplementation(
    private val apiService: ApiService,
    private val apiResponseHandler: ApiResponseHandler
): CandidateRepository {

    override suspend fun getCandidates(electionId: Int): ApiResult<List<Candidate>> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.getCandidates(electionId) },
            transform = {
                it?.map { candidateDTO -> candidateDTO.toDomainModel() } ?: emptyList()
            }
        )
    }

    private fun CandidateDTO.toDomainModel(): Candidate {
        return Candidate(id, electionId, name, party, slogan)
    }
}