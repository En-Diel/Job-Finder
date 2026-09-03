package com.endiel.jobfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.endiel.jobfinder.data.local.dao.SavedJobDao

@Database(
    [SavedJobEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class) // Registering the converter
abstract class JobFinderDatabase: RoomDatabase() {
    abstract fun savedJobDao(): SavedJobDao
}