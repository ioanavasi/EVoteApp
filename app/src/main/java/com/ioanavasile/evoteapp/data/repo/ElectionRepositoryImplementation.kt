package com.ioanavasile.evoteapp.data.repo

import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.model.ElectionDTO
import com.ioanavasile.evoteapp.domain.model.Election
import com.ioanavasile.evoteapp.domain.repo.ElectionRepository

class ElectionRepositoryImplementation(
    private val apiService: ApiService,
    private val apiResponseHandler: ApiResponseHandler
): ElectionRepository {

    override suspend fun getElections(): ApiResult<List<Election>> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.getElections() },
            transform = {
                it?.map { electionDTO -> electionDTO.toDomainModel() } ?: emptyList()
            }
        )
    }

    private fun ElectionDTO.toDomainModel(): Election {
        return Election(id, name)
    }

}