package com.endiel.jobfinder.ui.screens.jobdetails

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.endiel.jobfinder.model.ApplicationStatus
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
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
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
                text = job?.jobTitle ?: "Unknown Job",
                style = MaterialTheme.typography.headlineSmall
            )

            Text(
                text = job?.companyName ?: "Unknown Company",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = job?.location ?: "Unknown Location")

            HorizontalDivider()
            ApplicationStatus(
                status = application.status,
                onStatusChange = { newStatus ->
                    viewModel.updateApplicationStatus(
                        applicationId = application.id,
                        status = newStatus
                    )
                })

            HorizontalDivider()

            Text(
                text = "APPLICANT INFORMATION",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = application.fullName,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = application.email)
            Text(text = application.phone)

            HorizontalDivider()
            Text(
                text = "COVER LETTER",
                style = MaterialTheme.typography.titleMedium
            )
            Text(text = application.coverLetter)
        }
    }
}

@Composable
private fun ApplicationStatus(
    status: ApplicationStatus,
    onStatusChange: (ApplicationStatus) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "APPLICATION STATUS", style = MaterialTheme.typography.labelMedium)
        Text(text = status.displayName(), style = MaterialTheme.typography.titleMedium)
        Button(onClick = { expanded = true }) { Text("Change Status") }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            ApplicationStatus.entries.forEach { newStatus ->
                DropdownMenuItem(
                    text = { Text(newStatus.displayName()) },
                    onClick = {
                        onStatusChange(newStatus)
                        expanded = false
                    }
                )
            }
        }
    }
}