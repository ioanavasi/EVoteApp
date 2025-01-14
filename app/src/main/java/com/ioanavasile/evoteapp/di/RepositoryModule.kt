package com.ioanavasile.evoteapp.di

import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.repo.CandidateRepositoryImplementation
import com.ioanavasile.evoteapp.data.repo.ElectionRepositoryImplementation
import com.ioanavasile.evoteapp.data.repo.UserRepositoryImplementation
import com.ioanavasile.evoteapp.data.repo.VoteRepositoryImplementation
import com.ioanavasile.evoteapp.domain.repo.CandidateRepository
import com.ioanavasile.evoteapp.domain.repo.ElectionRepository
import com.ioanavasile.evoteapp.domain.repo.UserRepository
import com.ioanavasile.evoteapp.domain.repo.VoteRepository
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

    @Provides
    @Singleton
    fun provideVoteRepository(apiService: ApiService, apiResponseHandler: ApiResponseHandler):
            VoteRepository = VoteRepositoryImplementation(apiService, apiResponseHandler)

    @Provides
    @Singleton
    fun provideCandidateRepository(apiService: ApiService, apiResponseHandler: ApiResponseHandler):
            CandidateRepository = CandidateRepositoryImplementation(apiService, apiResponseHandler)

    @Provides
    @Singleton
    fun provideElectionRepository(apiService: ApiService, apiResponseHandler: ApiResponseHandler):
            ElectionRepository = ElectionRepositoryImplementation(apiService, apiResponseHandler)
}