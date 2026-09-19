package com.endiel.jobfinder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.model.ApplicationStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface JobApplicationDao {

    @Insert
    suspend fun insertApplication(applicationEntity: JobApplicationEntity)

    @Query("SELECT * FROM job_applications")
    fun getApplications(): Flow<List<JobApplicationEntity>>

    @Query("SELECT * FROM job_applications WHERE id = :applicationId")
    fun getApplicationId(applicationId: String): Flow<JobApplicationEntity?>

    @Query("UPDATE job_applications SET status = :status WHERE id = :applicationId")
    suspend fun updateStatus(applicationId: String, status: ApplicationStatus)
}