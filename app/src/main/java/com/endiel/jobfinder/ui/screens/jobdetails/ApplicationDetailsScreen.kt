package com.endiel.jobfinder.ui.screens.jobdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.endiel.jobfinder.ui.screens.applications.ApplicationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailsScreen(
    applicationId: Int,
    onBackClick: () -> Unit
) {
    val viewModel: ApplicationDetailsViewModel = hiltViewModel()
    val item by viewModel.application.collectAsState()

    LaunchedEffect(applicationId) {
        viewModel.loadApplication(applicationId)
    }

    if (item == null) {
        return
    }

    val application = item!!.application
    val job = item!!.job

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Application Details")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = job?.jobTitle ?: "Unknown Job"
            )

            Text(
                text = job?.companyName ?: "Unknown Company"
            )

            Text(
                text = job?.location ?: "Unknown Location"
            )

            Text(
                text = "Applicant"
            )

            Text(
                text = application.fullName
            )

            Text(
                text = "Email"
            )

            Text(
                text = application.email
            )

            Text(
                text = "Phone"
            )

            Text(
                text = application.phone
            )

            Text(
                text = "Cover Letter"
            )

            Text(
                text = application.coverLetter
            )

            Text(
                text = "Status: ${application.status.displayName()}"
            )
        }
    }
}