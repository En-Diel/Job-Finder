package com.endiel.jobfinder.di

import android.content.Context
import androidx.room.Room
import com.endiel.jobfinder.data.local.JobFinderDatabase
import com.endiel.jobfinder.data.local.dao.JobApplicationDao
import com.endiel.jobfinder.data.local.dao.SavedJobDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Tells hilt this is a conf file for dependencies
// Ensures object lives as long as the app does
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides // Tells hilt how to create a specific instance
    @Singleton // Ensures only one instance of DB is created and shared
    // Hilt automatically provides the app context so you can initialize Room
    fun provideDatabase(@ApplicationContext context: Context): JobFinderDatabase {
        return Room.databaseBuilder(
                context,
                JobFinderDatabase::class.java,
                "job_finder_database"
            ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideSavedJobDao(database: JobFinderDatabase): SavedJobDao {
        return database.savedJobDao()
    }

    @Provides
    fun provideJobApplicationDao(database: JobFinderDatabase): JobApplicationDao {
        return database.jobApplicationDao()
    }
}