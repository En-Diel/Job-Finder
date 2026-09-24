package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.remote.JobsRemoteDataSource
import com.endiel.jobfinder.data.remote.mapper.toJob
import com.endiel.jobfinder.model.Job
import com.endiel.jobfinder.ui.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JobsRepository @Inject constructor(private val remoteDataSource: JobsRemoteDataSource) {

    fun getJobs(query: String? = null, location: String? = null): Flow<List<Job>> = flow {
        val jobs = remoteDataSource.getJobs(query, location).map { it.toJob() }
        emit(jobs)
    }

    fun getJobById(jobId: String): Job? {
        return sampleJobs.find { it.id == jobId }
    }
}