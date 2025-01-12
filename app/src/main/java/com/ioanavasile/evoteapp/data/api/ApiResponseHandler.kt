package com.ioanavasile.evoteapp.data.api

import retrofit2.Response

class ApiResponseHandler {

    // Generic function to handle API calls and map responses
    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        transform: (T) -> R
    ): ApiResult<R> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ApiResult.Success(transform(body))
                } else {
                    ApiResult.Error("Empty response body")
                }
            } else {
                ApiResult.Error(response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "An unexpected error occurred")
        }
    }
}