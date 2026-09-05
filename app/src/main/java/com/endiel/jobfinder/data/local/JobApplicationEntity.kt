package com.endiel.jobfinder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.endiel.jobfinder.model.ApplicationStatus

@Entity(tableName = "job_applications")
data class JobApplicationEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val jobId: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val coverLetter: String,
    val status: ApplicationStatus = ApplicationStatus.SUBMITTED
)