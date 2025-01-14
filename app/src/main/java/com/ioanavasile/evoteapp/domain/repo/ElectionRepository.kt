package com.ioanavasile.evoteapp.domain.repo

import com.ioanavasile.evoteapp.data.api.ApiResult
import com.ioanavasile.evoteapp.domain.model.Election

interface ElectionRepository {

    suspend fun getElections(): ApiResult<List<Election>>
}