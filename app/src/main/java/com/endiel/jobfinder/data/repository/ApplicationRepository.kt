package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.data.local.dao.JobApplicationDao
import com.endiel.jobfinder.model.ApplicationStatus
import com.endiel.jobfinder.model.JobApplication
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepository @Inject constructor(
    private val jobApplicationDao: JobApplicationDao,
    private val firestoreApplicationRepository: FirestoreApplicationRepository,
    private val authRepository: AuthRepository
) {
    // SUBMIT APPLICATION
    suspend fun submitApplication(application: JobApplication) {

        val userId = authRepository.getCurrentUserId() ?: return

        val entity = JobApplicationEntity(
            id = application.id,
            userId = userId,
            jobId = application.jobId,
            fullName = application.fullName,
            email = application.email,
            phone = application.phone,
            coverLetter = application.coverLetter,
            status = application.status
        )
        jobApplicationDao.insertApplication(entity)
        firestoreApplicationRepository.saveApplication(application)
    }

    fun getApplications(userId: String): Flow<List<JobApplicationEntity>> {
        return jobApplicationDao.getApplications(userId)
    }

    fun getApplicationById(applicationId: String, userId: String): Flow<JobApplicationEntity?> {
        return jobApplicationDao.getApplicationId(applicationId = applicationId, userId = userId)
    }

    suspend fun updateApplicationStatus(applicationId: String, status: ApplicationStatus) {

        val userId = authRepository.getCurrentUserId() ?: return

        jobApplicationDao.updateStatus(
            applicationId = applicationId, userId = userId, status = status
        )
        firestoreApplicationRepository.updateApplicationStatus(
            applicationId = applicationId, status = status
        )
    }

    // SYNC APPLICATIONS
    suspend fun syncApplications() {

        val userId = authRepository.getCurrentUserId() ?: return
        val cloudApplications = firestoreApplicationRepository.getApplications()

        cloudApplications.forEach { application ->
            val entity = JobApplicationEntity(
                id = application.id,
                userId = userId,
                jobId = application.jobId,
                fullName = application.fullName,
                email = application.email,
                phone = application.phone,
                coverLetter = application.coverLetter,
                status = application.status
            )
            jobApplicationDao.insertApplication(entity)
        }
    }
}