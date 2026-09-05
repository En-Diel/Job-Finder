package com.endiel.jobfinder.ui.screens.applications

import com.endiel.jobfinder.data.local.JobApplicationEntity
import com.endiel.jobfinder.model.Job

data class ApplicationItem (
    val application: JobApplicationEntity,
    val job: Job?
)