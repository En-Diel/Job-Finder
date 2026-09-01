package com.endiel.jobfinder.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.endiel.jobfinder.R
import com.endiel.jobfinder.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            // Bottom navigation here
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState() // We'll change this scroll as the data scales up
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier) {
                        Row() { Text("Good Morning") }
                        Row() { Text("John Obwana") }
                    }
                }
            }

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