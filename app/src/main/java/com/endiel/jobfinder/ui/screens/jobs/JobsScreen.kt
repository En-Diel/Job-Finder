package com.endiel.jobfinder.ui.screens.jobs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.endiel.jobfinder.R
import com.endiel.jobfinder.data.repository.sampleJobs
import com.endiel.jobfinder.ui.components.BottomNavBar
import com.endiel.jobfinder.ui.components.CategoryChip
import com.endiel.jobfinder.ui.components.JobCard
import com.endiel.jobfinder.ui.components.JobSearchBar

@Composable
fun JobsScreen(navController: NavController) {

    val viewModel: JobsViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val filteredJobs by viewModel.filteredJobs.collectAsState()
    val savedJobs by viewModel.savedJobs.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.Start
        ) {

            // CATEGORY FILTER CHIPS
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(viewModel.categories) { category ->
                    CategoryChip(
                        category = category,
                        selected = selectedCategory == category,
                        onClick = {
                            viewModel.onCategorySelected(category)
                        }
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                // SEARCH BAR
                JobSearchBar(
                    searchQuery,
                    onQueryChange = { query ->
                        viewModel.onSearchQueryChange(query)
                                    },
                        modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(text = "${filteredJobs.size} jobs found", modifier = Modifier.padding(start = 16.dp))
                // SEARCH RESULTS
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier =Modifier.align(Alignment.CenterHorizontally)
                    )
                } else if (uiState.error != null) {
                    Column(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = uiState.error ?: "Something went wrong")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { viewModel.loadJobs() }
                        ) { Text("Retry") }
                    }
                } else if (filteredJobs.isEmpty()) {
                    Column() {
                        Text(text = "No jobs found", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Try searching for a different job title or company.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                } else {
                    PullToRefreshBox(
                        uiState.isLoading,
                        onRefresh = { viewModel.loadJobs() },
                        modifier = Modifier.fillMaxWidth().weight(1f)
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(filteredJobs) { job ->
                                JobCard(
                                    job = job,
                                    isSaved = savedJobs.any { it.id == job.id },
                                    onSaveClick = {
                                        viewModel.toggleSaveJob(job)
                                    },
                                    onClick = {
                                        navController.navigate("job_details/${job.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}