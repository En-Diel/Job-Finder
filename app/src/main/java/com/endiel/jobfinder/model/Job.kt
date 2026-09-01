package com.endiel.jobfinder.model

data class Job(
    val id: String,
    val companyName: String,
    val jobTitle: String,
    val location: String,
    val salary: String,
    val category: String
)