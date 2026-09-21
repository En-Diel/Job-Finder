package com.endiel.jobfinder.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// The engine that implements api service
object RetrofitInstance {

    private const val BASE_URL = "https://api.adzuna.com/v1/api/"

    val api: JobApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build().create(JobApiService::class.java)
    }
}