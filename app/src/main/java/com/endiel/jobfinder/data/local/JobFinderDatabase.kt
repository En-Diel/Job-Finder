package com.endiel.jobfinder.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.endiel.jobfinder.data.local.dao.SavedJobDao

@Database(
    [SavedJobEntity::class],
    version = 1, exportSchema = false
)
abstract class JobFinderDatabase: RoomDatabase() {
    abstract fun savedJobDao(): SavedJobDao
}