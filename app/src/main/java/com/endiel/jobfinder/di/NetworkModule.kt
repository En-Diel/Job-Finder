package com.endiel.jobfinder.di

import com.endiel.jobfinder.data.remote.JobApiService
import com.endiel.jobfinder.data.remote.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJobApiService(): JobApiService {
        return RetrofitInstance.api
    }
}