package com.endiel.jobfinder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.model.JobApplication
import kotlinx.coroutines.flow.Flow

@Dao
interface JobApplicationDao {

    @Insert
    suspend fun insertApplication(applicationEntity: JobApplicationEntity)

    @Query("SELECT * FROM job_applications")
    fun getApplications(): Flow<List<JobApplicationEntity>>
}