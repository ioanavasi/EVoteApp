package com.ioanavasile.evoteapp.data.api

import com.ioanavasile.evoteapp.data.model.UserCredentialsDTO
import com.ioanavasile.evoteapp.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun register(@Body user: UserDTO): Response<Unit>

    @POST("login")
    suspend fun login(@Body userCredentialsDTO: UserCredentialsDTO): Response<Unit>

    @POST("logout")
    suspend fun logout(): Response<Unit>
}