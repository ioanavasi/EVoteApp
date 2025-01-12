package com.ioanavasile.evoteapp.di

import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.repo.UserRepositoryImplementation
import com.ioanavasile.evoteapp.domain.repo.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService, apiResponseHandler: ApiResponseHandler):
            UserRepository = UserRepositoryImplementation(apiService, apiResponseHandler)
}