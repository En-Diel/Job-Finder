package com.endiel.jobfinder.data.remote

import com.endiel.jobfinder.BuildConfig
import com.endiel.jobfinder.data.remote.dto.JobDto
import jakarta.inject.Inject

class JobsRemoteDataSource @Inject constructor(private val api: JobApiService) {
    suspend fun getJobs(): List<JobDto> {

        val response = api.getJobs(
            country = "gb",
            page = 1,
            appId = BuildConfig.ADZUNA_APP_ID,
            appKey = BuildConfig.ADZUNA_APP_KEY,
            resultPerPage = 20,
            query = "software developer"
        )
        return response.results
    }
}