package com.endiel.jobfinder.model

import java.util.UUID

data class JobApplication (
    val id: String = UUID.randomUUID().toString(),
    val jobId: String,
    val fullName: String,
    val email: String,
    val phone: String,
    val coverLetter: String
)