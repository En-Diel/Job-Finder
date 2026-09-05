package com.endiel.jobfinder.ui.screens.savedjobs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.endiel.jobfinder.ui.components.JobCard
import com.endiel.jobfinder.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedJobsScreen(onBackClick: () -> Unit, navController: NavController) {

    val viewModel: SavedJobsViewModel = hiltViewModel()
    val savedJobs by viewModel.savedJobs.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            TopAppBar(
                title = { Text("Saved jobs") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
            //val savedJobs = SavedJobsRepository.getSavedJobs()
            LazyColumn() {
                items(savedJobs) { job ->
                    JobCard(
                        job = job,
                        isSaved = true,
                        onSaveClick = {
                            viewModel.removeJob(job)
                        },
                        onClick = {
                            navController.navigate("job_details/${job.id}")
                        })
                }
            }
        }
    }
}