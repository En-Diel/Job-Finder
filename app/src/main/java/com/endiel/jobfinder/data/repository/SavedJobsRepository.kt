package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.model.Job

object SavedJobsRepository {

    private val savedJobs = mutableListOf<Job>()

    fun getSavedJobs(): List<Job> {
        return savedJobs
    }
    fun saveJob(job: Job) {
        if (!savedJobs.contains(job)) {
            savedJobs.add(job)
        }
    }
    fun removeJob(job: Job) {
        savedJobs.remove(job)
    }
    fun isJobSaved(job: Job): Boolean {
        return savedJobs.contains(job)
    }
}