package com.endiel.jobfinder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.model.ApplicationStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface JobApplicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApplication(applicationEntity: JobApplicationEntity)

    @Query("SELECT * FROM job_applications WHERE userId = :userId")
    fun getApplications(userId: String): Flow<List<JobApplicationEntity>>

    @Query("SELECT * FROM job_applications WHERE id = :applicationId AND userId = :userId")
    fun getApplicationId(applicationId: String, userId: String): Flow<JobApplicationEntity?>

    @Query("UPDATE job_applications SET status = :status WHERE id = :applicationId")
    suspend fun updateStatus(applicationId: String, status: ApplicationStatus)
}