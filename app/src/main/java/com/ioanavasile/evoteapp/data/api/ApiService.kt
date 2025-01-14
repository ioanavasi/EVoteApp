package com.ioanavasile.evoteapp.data.api

import com.ioanavasile.evoteapp.data.model.CandidateDTO
import com.ioanavasile.evoteapp.data.model.ElectionDTO
import com.ioanavasile.evoteapp.data.model.UserCredentialsDTO
import com.ioanavasile.evoteapp.data.model.UserDTO
import com.ioanavasile.evoteapp.data.model.VoteDTO
import com.ioanavasile.evoteapp.data.model.VoteHistoryDTO
import com.ioanavasile.evoteapp.data.model.VotedStatusDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    suspend fun register(@Body user: UserDTO): Response<Unit>

    @POST("login")
    suspend fun login(@Body userCredentialsDTO: UserCredentialsDTO): Response<Unit>

    @POST("logout")
    suspend fun logout(): Response<Unit>

    @POST("vote")
    suspend fun vote(@Body voteDTO: VoteDTO): Response<Unit>

    @GET("vote/status/{e_id}")
    suspend fun getVotedStatus(@Path("e_id") electionId: Int): Response<VotedStatusDTO?>

    @GET("vote/history")
    suspend fun getVoteHistory(): Response<List<VoteHistoryDTO>?>

    @GET("candidate/{e_id}")
    suspend fun getCandidates(@Path("e_id") electionId: Int): Response<List<CandidateDTO>?>

    @GET("election")
    suspend fun getElections(): Response<List<ElectionDTO>?>
}