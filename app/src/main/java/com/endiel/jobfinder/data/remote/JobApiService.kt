package com.endiel.jobfinder.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// API SERVICE (Describe the http request)
interface JobApiService {
    @GET("jobs/{country}/search/{page}")
    suspend fun getJobs(
        @Path("country") country: String,
        @Path("page") page: Int,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("results_per_page") resultPerPage: Int = 20,
        @Query("what") query: String? = null,
        @Query("where") location: String? = null,
        @Query("content-type") contentType: String = "application/json"
    ): JobSearchResponse
}