package com.endiel.jobfinder.ui.screens.savedjobs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.endiel.jobfinder.data.repository.SavedJobsRepository
import com.endiel.jobfinder.ui.components.JobCard

@Composable
fun SavedJobsScreen(navController: NavController) {

    val savedJobs = SavedJobsRepository.getSavedJobs()
    LazyColumn() {
        items(savedJobs) { job ->
            JobCard(
                job = job,
                onClick = {
                navController.navigate("job_details/${job.id}")
            })
        }
    }
}