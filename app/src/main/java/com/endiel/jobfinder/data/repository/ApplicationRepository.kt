package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.data.local.dao.JobApplicationDao
import com.endiel.jobfinder.model.ApplicationStatus
import com.endiel.jobfinder.model.JobApplication
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepository @Inject constructor(
    private val jobApplicationDao: JobApplicationDao,
    private val firestoreApplicationRepository: FirestoreApplicationRepository
) {

    suspend fun submitApplication(application: JobApplication) {
        val entity = JobApplicationEntity(
            id = application.id,
            jobId = application.jobId,
            fullName = application.fullName,
            email = application.email,
            phone = application.phone,
            coverLetter = application.coverLetter
        )
        jobApplicationDao.insertApplication(entity)
        firestoreApplicationRepository.saveApplication(application)
    }

    fun getApplications(): Flow<List<JobApplicationEntity>> {
        return jobApplicationDao.getApplications()
    }

    fun getApplicationById(applicationId: String): Flow<JobApplicationEntity?> {
        return jobApplicationDao.getApplicationId(applicationId)
    }

    suspend fun updateApplicationStatus(applicationId: String, status: ApplicationStatus) {
        jobApplicationDao.updateStatus(
            applicationId = applicationId,
            status = status
        )
    }
}