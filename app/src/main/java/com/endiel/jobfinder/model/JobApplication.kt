package com.endiel.jobfinder.model

data class JobApplication (
    val jobId: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val coverLetter: String
)