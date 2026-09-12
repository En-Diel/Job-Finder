package com.endiel.jobfinder.ui.screens.applyjob

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyJobScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    jobId: String?,
    onSubmitSuccess: () -> Unit,
    viewModel: ApplyJobViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.isSubmitted) {
        if (uiState.isSubmitted) {
            onSubmitSuccess()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Apply for job")
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
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Job ID: $jobId"
            )
            OutlinedTextField(
                value = uiState.fullName,
                onValueChange = { viewModel.onFullNameChange(it) },
                label = { Text("Full name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = uiState.phone,
                onValueChange = { viewModel.onPhoneChange(it) },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = uiState.coverLetter,
                onValueChange = { viewModel.onCoverLetterChange(it) },
                label = { Text("Cover Letter") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            // Means only execute let if uiState.error is not null
            uiState.error?.let { error ->
                Text(
                    text = error
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            // SUBMIT JOB BUTTON
            Button(
                onClick = {
                    if (jobId != null) {
                        viewModel.submitApplication(jobId)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSubmitting
            ) {
                if (uiState.isSubmitting) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Submit application")
                }
            }
            if (uiState.isSubmitted) {
                Text(
                    text = "Application submitted successfully!"
                )
            }
        }
    }
}