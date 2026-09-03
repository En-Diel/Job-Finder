package com.endiel.jobfinder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.endiel.jobfinder.data.local.SavedJobEntity
import kotlinx.coroutines.flow.Flow

// Dao defines methods used by the app to access DB
@Dao
interface SavedJobDao {

    @Query("SELECT * FROM saved_jobs")
    fun getSavedJobs(): Flow<List<SavedJobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJob(job: SavedJobEntity)

    @Delete
    suspend fun delete(job: SavedJobEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_jobs WHERE id = :jobId)")
    fun isJobSaved(jobId: String): Flow<Boolean>
}