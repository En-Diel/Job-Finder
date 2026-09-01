package com.endiel.jobfinder.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
            // Welcome message
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,

                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier) {
                        Row() { Text("Good morning") }
                        Row() { Text("John Obwana") }
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text("Find your dream job", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.width(16.dp))
            // Search Bar
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                placeholder = {
                    Text("Search jobs...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(32.dp)
            )

            Text("Home Screen")
            Button(onClick = { navController.navigate(Screen.Search.route) }) { Text("Search") }
            Button(onClick = { navController.navigate(Screen.Profile.route) }) { Text("Profile") }
            Button(onClick = { navController.navigate(Screen.SavedJobs.route) }) { Text("Saved Jobs") }
            Button(onClick = { navController.navigate(Screen.JobDetails.route) }) { Text("Job Details") }

        }
    }
}