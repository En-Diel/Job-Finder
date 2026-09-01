package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.model.Job

val sampleJobs = listOf(
    Job(
        id = "1",
        companyName = "Google",
        jobTitle = "Android developer",
        location = "Toront, Canada",
        salary = "$90k - %200k",
        category = "Technology"
    ),
    Job(
        id = "2",
        companyName = "Microsoft",
        jobTitle = "Software Engineer",
        location = "Vancouver, Canada",
        salary = "$100k - $140k",
        category = "Technology"
    ),

    Job(
        id = "3",
        companyName = "Adobe",
        jobTitle = "Product Designer",
        location = "Remote",
        salary = "$80k - $110k",
        category = "Design"
    ),

    Job(
        id = "4",
        companyName = "Deloitte",
        jobTitle = "Financial Analyst",
        location = "Toronto, Canada",
        salary = "$70k - $95k",
        category = "Finance"
    )
)