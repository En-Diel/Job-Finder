package com.endiel.jobfinder.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.endiel.jobfinder.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen")
        Button(onClick = { navController.navigate(Screen.Search.route) }) { Text("Search") }
        Button(onClick = { navController.navigate(Screen.Profile.route) }) { Text("Profile") }
        Button(onClick = { navController.navigate(Screen.SavedJobs.route) }) { Text("Saved Jobs") }
        Button(onClick = { navController.navigate(Screen.JobDetails.route) }) { Text("Job Details") }
    }
}