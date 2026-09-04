/*
package com.endiel.jobfinder.ui.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.endiel.jobfinder.data.repository.sampleJobs
import com.endiel.jobfinder.model.Job
import com.endiel.jobfinder.ui.components.JobCard
import com.endiel.jobfinder.ui.components.JobSearchBar

@Composable
fun SearchScreen(navController: NavController) {

    var searchQuery by remember {
        mutableStateOf("")
    }

    val filteredJobs = sampleJobs.filter { job ->

        job.jobTitle.contains(
            searchQuery,
            ignoreCase = true
        ) || job.companyName.contains(
            searchQuery,
            ignoreCase = true
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
    ) {
        JobSearchBar(
            searchQuery, onQueryChange = { searchQuery = it },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(text = "Search results", modifier = Modifier.padding(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(filteredJobs) { job ->
                JobCard(
                    job = job,
                    onClick = {
                        navController.navigate("job_details${job.id}")
                    }
                )
            }
        }
    }
}

 */