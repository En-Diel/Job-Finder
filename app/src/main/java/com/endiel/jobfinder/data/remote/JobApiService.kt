package com.endiel.jobfinder.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

// API SERVICE (Describe the http request)
interface JobApiService {
    @GET("jobs/{country}/search/{page}")
    suspend fun getJobs(
        @Path("country") country: String,
        @Path("page") page: Int,
        @Path("app_id") appId: String,
        @Path("app_key") appKey: String,
        @Path("result_per_page") resultPerPage: Int = 20,
        @Path("what") query: String? = null,
        @Path("where") location: String? = null,
        @Path("content_type") contentType: String = "application/json"
    ): JobSearchResponse
}