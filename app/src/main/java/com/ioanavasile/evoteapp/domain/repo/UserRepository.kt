package com.ioanavasile.evoteapp.domain.repo

import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.User
import com.ioanavasile.evoteapp.domain.model.UserCredentials

interface UserRepository {

    suspend fun register(user: User): ApiResult<Unit>

    suspend fun login(userCredentials: UserCredentials): ApiResult<Unit>

    suspend fun logout(): ApiResult<Unit>
}