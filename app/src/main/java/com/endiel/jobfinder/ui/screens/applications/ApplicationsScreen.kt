package com.endiel.jobfinder.ui.screens.applications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.endiel.jobfinder.data.local.JobApplicationEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationsScreen(
    onApplicationClick: (Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val viewModel: ApplicationsViewModel = hiltViewModel()
    val applications by viewModel.applications.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(), {
        TopAppBar(
        title = { Text("My applications") },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back"
                )
            }
        }
    )
    }) { innerPadding ->

        if (applications.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "You haven't submitted any applications yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(applications) { item ->
                    ApplicationCard(
                        onClick = { onApplicationClick(item.application.id) },
                        item = item
                    )
                }
            }
        }
    }
}

@Composable
private fun ApplicationCard(
    onClick: () -> Unit,
    item: ApplicationItem,
    modifier: Modifier = Modifier
) {

    val application = item.application
    val job = item.job

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = job?.jobTitle ?: "Unknown Job")
            Text(text = job?.companyName ?: "Unknown Company")
            Text(text = job?.location ?: "Unknown Location")
            Text(text = "Applicant: ${application.fullName}")
            Text(text = "Email: ${application.email}")
            Text(text = "Phone: ${application.phone}")
            Text(text = "Cover Letter")
            Text(
                text = application.coverLetter,
                maxLines = 3
            )
            Text(
                text = "Status: ${application.status.displayName()}"
            )
        }
    }
}