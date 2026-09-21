package com.endiel.jobfinder.data.remote

import com.endiel.jobfinder.data.remote.dto.JobDto

data class JobSearchResponse (
    val results: List<JobDto>
)