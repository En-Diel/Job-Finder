package com.endiel.jobfinder.ui.screens.savedjobs

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.endiel.jobfinder.ui.components.JobCard

@Composable
fun SavedJobsScreen(navController: NavController) {

    val viewModel: SavedJobsViewModel = viewModel()
    val savedJobs by viewModel.savedJobs.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSavedJobs()
    }

    //val savedJobs = SavedJobsRepository.getSavedJobs()
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