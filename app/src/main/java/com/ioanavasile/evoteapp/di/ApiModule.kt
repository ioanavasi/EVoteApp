package com.ioanavasile.evoteapp.di

import com.ioanavasile.evoteapp.data.api.ApiResponseHandler
import com.ioanavasile.evoteapp.data.api.ApiService
import com.ioanavasile.evoteapp.data.api.SessionCookieJar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "http://10.0.2.2:5000/"

    @Provides
    @Singleton
    fun provideSessionCookieJar(): SessionCookieJar = SessionCookieJar()

    @Provides
    @Singleton
    fun provideOkHttpClient(sessionCookieJar: SessionCookieJar): OkHttpClient =
        OkHttpClient.Builder()
            .cookieJar(sessionCookieJar)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAPIResponseHandler(): ApiResponseHandler = ApiResponseHandler()
}