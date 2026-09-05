package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.data.local.dao.JobApplicationDao
import com.endiel.jobfinder.model.JobApplication
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class ApplicationRepository @Inject constructor(private val jobApplicationDao: JobApplicationDao) {
    suspend fun submitApplication(application: JobApplication) {
        val entity = JobApplicationEntity(
            jobId = application.jobId,
            fullName = application.fullName,
            email = application.email,
            phone = application.phone,
            coverLetter = application.coverLetter
        )
        jobApplicationDao.insertApplication(entity)
    }
}