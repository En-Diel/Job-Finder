package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.model.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JobsRepository {
    fun getJobs(): Flow<List<Job>> = flow {
        // By using emit, we assume the data come from API
        emit(sampleJobs)
    }
}