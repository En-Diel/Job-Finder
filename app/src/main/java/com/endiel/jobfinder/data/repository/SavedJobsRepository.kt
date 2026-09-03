package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.data.local.SavedJobEntity
import com.endiel.jobfinder.data.local.dao.SavedJobDao
import com.endiel.jobfinder.model.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Repository has to access DAO first then Dao access DB
class SavedJobsRepository(private val savedJobDao: SavedJobDao) {
    fun getSavedJobs(): Flow<List<Job>> {
        return savedJobDao.getSavedJobs().map { entities ->
            entities.map { entity ->
                Job(
                    id = entity.id,
                    companyName = entity.companyName,
                    jobTitle = entity.jobTitle,
                    location = entity.location,
                    salary = entity.salary,
                    category = entity.category,
                    description = entity.description,
                    jobType = entity.jobType,
                    jobPosition = entity.jobPosition,
                    requirements = entity.requirements,
                    responsibilities = entity.responsibilities
                )
            }
        }
    }
    suspend fun saveJob(job: Job) {

        val entity = SavedJobEntity(
            id = job.id,
            companyName = job.companyName,
            jobTitle = job.jobTitle,
            location = job.location,
            salary = job.salary,
            category = job.category,
            description = job.description,
            jobType = job.jobType,
            jobPosition = job.jobPosition,
            requirements = job.requirements,
            responsibilities = job.responsibilities
        )

        savedJobDao.saveJob(entity)
    }
    suspend fun removeJob(job: Job) {
        val entity = SavedJobEntity(
            id = job.id,
            companyName = job.companyName,
            jobTitle = job.jobTitle,
            location = job.location,
            salary = job.salary,
            category = job.category,
            description = job.description,
            jobType = job.jobType,
            jobPosition = job.jobPosition,
            requirements = job.requirements,
            responsibilities = job.responsibilities
        )
        savedJobDao.delete(entity)
    }
    suspend fun isJobSaved(jobId: String): Boolean{
        return savedJobDao.isJobSaved(jobId)
    }
}