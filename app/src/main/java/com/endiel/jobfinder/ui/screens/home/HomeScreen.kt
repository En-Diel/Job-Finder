package com.endiel.jobfinder.ui.screens.home

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.endiel.jobfinder.R
import com.endiel.jobfinder.data.repository.sampleJobs
import com.endiel.jobfinder.model.Job
import com.endiel.jobfinder.ui.components.BottomNavBar
import com.endiel.jobfinder.ui.components.CategoryChip
import com.endiel.jobfinder.ui.components.JobCard
import com.endiel.jobfinder.ui.components.JobSearchBar
import com.endiel.jobfinder.ui.screens.jobs.JobsViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: JobsViewModel = hiltViewModel()
    val featuredJobs by viewModel.featuredJobs.collectAsStateWithLifecycle()

    val savedJobs by viewModel.savedJobs.collectAsState()
    // We'll use this when we create a viewModel
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    // This data will be coming from API later
    val categories = listOf(
        "All",
        "Technology",
        "Design",
        "Finance",
        "Marketing",
        "Sales",
        "Engineering"
    )
    // FILTERING
    val filteredJobs = sampleJobs.filter { job ->
        val matchesSearch = job.jobTitle.contains(
            searchQuery,
            ignoreCase = true
        ) || job.companyName.contains(
            searchQuery,
            ignoreCase = true
        )

        val matchesCategory =
            selectedCategory == "All" ||
                    job.category.equals(
                        selectedCategory,
                        ignoreCase = true
                    )

        matchesSearch && matchesCategory
    }

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
            // WELCOME MESSAGE
            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column() {
                        Row() { Text("Good morning", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)) }
                        Row() { Text("John Obwana", style = MaterialTheme.typography.labelLarge) }
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Find your dream job", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(start = 16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            // SEARCH BAR
            JobSearchBar(
                searchQuery, onQueryChange = { searchQuery = it },
                modifier = Modifier.padding(horizontal = 16.dp) ////////
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Categories",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            // CATEGORY FILTER CHIPS
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        category = category,
                        selected = selectedCategory == category,
                        onClick = {
                            selectedCategory = category
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Recommended jobs",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // JOBS CARD
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredJobs) { job ->
                    JobCard(
                        job = job,
                        isSaved = savedJobs.any { it.id == job.id },
                        onSaveClick = {
                            viewModel.toggleSaveJob(job)
                        },
                        onClick = {
                            //println("JOB CARD CLICKED: ${job.id}") // Testing the route
                            navController.navigate("job_details/${job.id}")
                    })
                }
            }
        }
    }
}