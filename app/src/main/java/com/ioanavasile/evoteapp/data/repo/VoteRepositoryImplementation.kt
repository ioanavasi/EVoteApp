package com.ioanavasile.evoteapp.data.repo

import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.model.VoteDTO
import com.ioanavasile.evoteapp.data.model.VoteHistoryDTO
import com.ioanavasile.evoteapp.domain.model.Vote
import com.ioanavasile.evoteapp.domain.model.VoteHistory
import com.ioanavasile.evoteapp.domain.repo.VoteRepository

class VoteRepositoryImplementation(
    private val apiService: ApiService,
    private val apiResponseHandler: ApiResponseHandler
): VoteRepository {

    override suspend fun vote(vote: Vote): ApiResult<Unit> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.vote(vote.toDBModel()) },
            transform = { }
        )
    }

    override suspend fun hasVoted(electionId: Int): ApiResult<Boolean> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.getVotedStatus(electionId) },
            transform = {
                it?.status == "YES"
            }
        )
    }

    override suspend fun getVoteHistory(): ApiResult<List<VoteHistory>> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.getVoteHistory() },
            transform = {
                it?.map { voteHistoryDTO -> voteHistoryDTO.toDomainModel() } ?: emptyList()
            }
        )
    }

    private fun Vote.toDBModel(): VoteDTO {
        return VoteDTO(electionId, candidateId, timestamp)
    }

    private fun VoteHistoryDTO.toDomainModel(): VoteHistory {
        return VoteHistory(electionName, candidateName, candidateParty, timestamp)
    }

}