package com.endiel.jobfinder.data.repository

import com.endiel.jobfinder.model.JobApplication
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class ApplicationRepository {
    suspend fun submitApplication(application: JobApplication) {
        // Simulate submitting to a server
        delay(1000.milliseconds)
    }
}