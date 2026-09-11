package com.endiel.jobfinder.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.endiel.jobfinder.model.UserProfile
import com.endiel.jobfinder.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onSavedJobsClick: () -> Unit,
    onApplicationsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Profile")
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
                },
                actions = {
                    if (!uiState.isEditing) {
                        IconButton(
                            onClick = {
                                viewModel.startEditing()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Profile"
                            )
                        }
                    } else {
                        TextButton(
                            onClick = {
                                viewModel.saveProfile()
                            }
                        ) {
                            Text("Save")
                        }
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            if (uiState.isEditing) {

                OutlinedTextField(
                    value = uiState.fullName,
                    onValueChange = {
                        viewModel.onFullNameChange(it)
                    },
                    label = {
                        Text("Full Name")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = {
                        viewModel.onEmailChange(it)
                    },
                    label = {
                        Text("Email")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = uiState.phone,
                    onValueChange = {
                        viewModel.onPhoneChange(it)
                    },
                    label = {
                        Text("Phone")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = uiState.location,
                    onValueChange = {
                        viewModel.onLocationChange(it)
                    },
                    label = {
                        Text("Location")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            } else {
                Text(
                    text = uiState.fullName,
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = uiState.email,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = uiState.phone
                )

                Text(
                    text = uiState.location
                )
            }
            Button(
                onClick = onSavedJobsClick,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Saved Jobs") }
            Button(
                onClick = onApplicationsClick,
                modifier = Modifier.fillMaxWidth()
            ) { Text("My Applications") }
            Button(
                onClick = onSettingsClick,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Settings") }
            Button(
                onClick = onLogoutClick,
                modifier = Modifier.fillMaxWidth()
            ) { Text("Log out") }
        }
    }
}