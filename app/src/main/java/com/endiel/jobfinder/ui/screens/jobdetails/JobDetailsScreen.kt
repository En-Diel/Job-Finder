package com.endiel.jobfinder.ui.screens.jobdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.endiel.jobfinder.R
import com.endiel.jobfinder.model.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(
    job: Job,
    onBackClick: () -> Unit
) {
    // Instantiate viewModel
    //val viewModel: JobDetailsViewModel = viewModel()
    val viewModel: JobDetailsViewModel = hiltViewModel()
    // Observe state
    val isSaved by viewModel.isSaved.collectAsState()
    // Initialize viewModel bases on the current job
    LaunchedEffect(job.id) {
        viewModel.loadJob(job)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            TopAppBar(
                title = { Text("Job Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "company logo",
                modifier = Modifier.size(64.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(text = job.jobTitle)
            Text(text = job.companyName)
            Row {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text("Salary")
                    Text(text = job.salary)
                }
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text("Job type")
                    Text(text = job.jobType)
                }
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text("Position")
                    Text(text = job.jobPosition)
                }
            }
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text("Job description")
                Text(text = job.description)
                Text("Job responsibilities")
                job.responsibilities.forEach { responsibility ->
                    Text(text = ". $responsibility ")
                }
                job.requirements.forEach { requirement ->
                    Text(text = ". $requirement ")
                }
                Row() {
                    Button(
                        onClick = {
                            viewModel.toggleSaveJob(job)
                        }
                    ) {
                        Text(text = if (isSaved) "Job Saved" else "Save Job") }
                    Button(onClick = {}) { Text("Apply Now") }
                }
            }
        }
    }
}