package com.ioanavasile.evoteapp.domain.repo

import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.Vote
import com.ioanavasile.evoteapp.domain.model.VoteHistory

interface VoteRepository {

    suspend fun vote(vote: Vote): ApiResult<Unit>

    suspend fun hasVoted(electionId: Int): ApiResult<Boolean>

    suspend fun getVoteHistory(): ApiResult<List<VoteHistory>>
}