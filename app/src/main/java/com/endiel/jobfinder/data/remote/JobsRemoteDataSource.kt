package com.endiel.jobfinder.data.remote

import com.endiel.jobfinder.BuildConfig
import com.endiel.jobfinder.data.remote.dto.JobDto
import jakarta.inject.Inject

class JobsRemoteDataSource @Inject constructor(private val api: JobApiService) {
    suspend fun getJobs(query: String? = null, location: String? = null, page: Int = 1): List<JobDto> {

        val response = api.getJobs(
            country = "gb",
            page = page,
            appId = BuildConfig.ADZUNA_APP_ID,
            appKey = BuildConfig.ADZUNA_APP_KEY,
            resultPerPage = 20,
            query = query,
            location = location
        )
        return response.results
    }
}