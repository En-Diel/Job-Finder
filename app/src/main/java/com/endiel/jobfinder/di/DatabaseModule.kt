package com.endiel.jobfinder.di

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.endiel.jobfinder.data.local.JobFinderDatabase
import com.endiel.jobfinder.data.local.dao.JobApplicationDao
import com.endiel.jobfinder.data.local.dao.ProfileDao
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

    private val MIGRATION_5_6 = object : Migration(5, 6) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
                ALTER TABLE user_profile
                ADD COLUMN userId TEXT NOT NULL DEFAULT ''
                """.trimIndent()
            )
        }
    }

    private val MIGRATION_6_7 = object : Migration(6, 7) {
        override fun migrate(database: SupportSQLiteDatabase) {

            database.execSQL(
                """ CREATE TABLE user_profile_new (
                    userId TEXT NOT NULL, 
                    fullName TEXT NOT NULL, 
                    email TEXT NOT NULL, 
                    phone TEXT NOT NULL, 
                    location TEXT NOT NULL, 
                    PRIMARY KEY(userId)
                ) 
                """.trimIndent()
            )
            database.execSQL(
                """ INSERT INTO user_profile_new(
                userId, 
                fullName, 
                email, 
                phone, 
                location
            ) 
            SELECT 
                userId, 
                fullName, 
                email, 
                phone, 
                location 
            FROM user_profile 
            WHERE userId != '' 
            """.trimIndent()
            )
            database.execSQL(
                """ DROP TABLE user_profile 
                """.trimIndent()
            )
            database.execSQL(
                """ ALTER TABLE user_profile_new 
                    RENAME TO user_profile 
                """.trimIndent()
            )
        }
    }

    private val MIGRATION_7_8 = object : Migration(7, 8) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """ CREATE TABLE job_applications_new (
                    id TEXT NOT NULL, 
                    jobId TEXT NOT NULL, 
                    fullName TEXT NOT NULL, 
                    email TEXT NOT NULL, 
                    phone TEXT NOT NULL, 
                    coverLetter TEXT NOT NULL, 
                    status TEXT NOT NULL, 
                    PRIMARY KEY(id)
                    ) 
                """.trimIndent()
            )
            database.execSQL(
                """ DROP TABLE job_applications """.trimIndent()
            )
            database.execSQL(
                """ ALTER TABLE job_applications_new 
                    RENAME TO job_applications 
                """.trimIndent()
            )
        }
    }

    @Provides // Tells hilt how to create a specific instance
    @Singleton // Ensures only one instance of DB is created and shared
    // Hilt automatically provides the app context so you can initialize Room
    fun provideDatabase(@ApplicationContext context: Context): JobFinderDatabase {
        return Room.databaseBuilder(
            context, JobFinderDatabase::class.java, "job_finder_database"
        ).addMigrations(
            MIGRATION_5_6,
            MIGRATION_6_7,
            MIGRATION_7_8
        ).build()
    }

    @Provides
    fun provideSavedJobDao(database: JobFinderDatabase): SavedJobDao {
        return database.savedJobDao()
    }

    @Provides
    fun provideJobApplicationDao(database: JobFinderDatabase): JobApplicationDao {
        return database.jobApplicationDao()
    }

    @Provides
    fun provideProfileDao(database: JobFinderDatabase): ProfileDao {
        return database.profileDao()
    }
}