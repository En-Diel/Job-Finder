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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.endiel.jobfinder.R
import com.endiel.jobfinder.model.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobDetailsScreen(
    job: Job,
    onBackClick: () -> Unit
) {
    // Save state for save job button
    var isSaved by remember { mutableStateOf(false) }

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
                            isSaved = !isSaved
                        }
                    ) {
                        Text(text = if (isSaved) "Job Saved" else "Save Job") }
                    Button(onClick = {}) { Text("Apply Now") }
                }
            }
        }
    }
}