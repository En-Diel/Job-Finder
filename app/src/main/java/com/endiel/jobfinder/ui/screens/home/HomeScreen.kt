package com.endiel.jobfinder.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.endiel.jobfinder.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            // Bottom navigation here
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).verticalScroll(
                rememberScrollState() // We'll change this scroll as the data scales up
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Home Screen")
            Button(onClick = { navController.navigate(Screen.Search.route) }) { Text("Search") }
            Button(onClick = { navController.navigate(Screen.Profile.route) }) { Text("Profile") }
            Button(onClick = { navController.navigate(Screen.SavedJobs.route) }) { Text("Saved Jobs") }
            Button(onClick = { navController.navigate(Screen.JobDetails.route) }) { Text("Job Details") }
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
            Text("Home Screen")
        }
    }
}