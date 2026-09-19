package com.endiel.jobfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.endiel.jobfinder.data.local.dao.JobApplicationDao
import com.endiel.jobfinder.data.local.dao.ProfileDao
import com.endiel.jobfinder.data.local.dao.SavedJobDao

@Database(
    [
        SavedJobEntity::class,
        JobApplicationEntity::class,
        UserProfileEntity::class
    ],
    // We changed the version coz we changed the database schema by adding a new table
    version = 8, exportSchema = false
)
@TypeConverters(Converters::class) // Registering the converter
abstract class JobFinderDatabase: RoomDatabase() {
    // Add Dao inside the database
    abstract fun savedJobDao(): SavedJobDao
    abstract fun jobApplicationDao(): JobApplicationDao
    abstract fun profileDao(): ProfileDao
}