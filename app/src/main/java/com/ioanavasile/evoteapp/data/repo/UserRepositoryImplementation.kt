package com.ioanavasile.evoteapp.data.repo

import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.model.UserCredentialsDTO
import com.ioanavasile.evoteapp.data.model.UserDTO
import com.ioanavasile.evoteapp.domain.model.User
import com.ioanavasile.evoteapp.domain.model.UserCredentials
import com.ioanavasile.evoteapp.domain.repo.UserRepository

class UserRepositoryImplementation(
    private val apiService: ApiService,
    private val apiResponseHandler: ApiResponseHandler
) : UserRepository {

    override suspend fun register(user: User): ApiResult<Unit> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.register(user.toDBModel()) },
            transform = { }
        )
    }

    override suspend fun login(userCredentials: UserCredentials): ApiResult<Unit> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.login(userCredentials.toDBModel()) },
            transform = { }
        )
    }

    override suspend fun logout(): ApiResult<Unit> {
        return apiResponseHandler.safeApiCall(
            apiCall = { apiService.logout() },
            transform = { }
        )
    }

    private fun User.toDBModel(): UserDTO {
        return UserDTO(cnp, email, password, firstName, lastName)
    }

    private fun UserCredentials.toDBModel(): UserCredentialsDTO {
        return UserCredentialsDTO(cnp, password)
    }
}