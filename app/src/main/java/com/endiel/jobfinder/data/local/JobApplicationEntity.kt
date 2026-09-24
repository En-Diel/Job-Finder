package com.endiel.jobfinder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.endiel.jobfinder.model.ApplicationStatus

@Entity(tableName = "job_applications")
data class JobApplicationEntity (
    @PrimaryKey
    val id: String,

    val userId: String,
    val jobId: String,
    val jobTitle: String,
    val companyName: String,
    val location: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val coverLetter: String,
    val status: ApplicationStatus = ApplicationStatus.SUBMITTED
)