package com.endiel.jobfinder.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

// Entity represents how the job model is stored in database
@Entity(tableName = "saved_jobs")
data class SavedJobEntity(
    @PrimaryKey
    val id: String,
    val companyName: String,
    val jobTitle: String,
    val location: String,
    val salary: String,
    val category: String,
    val description: String,
    val jobType: String,
    val jobPosition: String,
    val requirements: List<String>,
    val responsibilities: List<String>
)