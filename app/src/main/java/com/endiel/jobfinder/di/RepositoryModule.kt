package com.endiel.jobfinder.di

import com.endiel.jobfinder.data.local.dao.SavedJobDao
import com.endiel.jobfinder.data.repository.JobsRepository
import com.endiel.jobfinder.data.repository.SavedJobsRepository
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
    fun provideSavedJobsRepository(savedJobDao: SavedJobDao): SavedJobsRepository {
        return SavedJobsRepository(savedJobDao)
    }

    @Provides
    @Singleton
    fun provideJobsRepository(): JobsRepository {
        return JobsRepository()
    }
}