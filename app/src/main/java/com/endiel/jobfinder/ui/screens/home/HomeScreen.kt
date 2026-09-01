package com.endiel.jobfinder.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.endiel.jobfinder.R
import com.endiel.jobfinder.ui.components.BottomNavBar

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(
                    rememberScrollState() // We'll change this scroll as the data scales up
                ),
            horizontalAlignment = Alignment.Start
        ) {
            // Welcome message
            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "profile picture",
                        modifier = Modifier.size(64.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier) {
                        Row() { Text("Good morning") }
                        Row() { Text("John Obwana") }
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text("Find your dream job", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
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
            Spacer(modifier = Modifier.width(16.dp))
            // Category filter chips
            FlowRow(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                //verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                FilterChip(selected = false, onClick = {}, label = {Text("Remote job")}, shape = CircleShape)
                FilterChip(selected = true, onClick = {}, label = {Text("Full time job")}, shape = CircleShape)
                FilterChip(selected = false, onClick = {}, label = {Text("Part time job")}, shape = CircleShape)
                FilterChip(selected = false, onClick = {}, label = {Text("Android developer")}, shape = CircleShape)
                FilterChip(selected = false, onClick = {}, label = {Text("Public relation")}, shape = CircleShape)
                FilterChip(selected = false, onClick = {}, label = {Text("More")}, shape = CircleShape)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text("Recommended jobs", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.width(16.dp))
            // Recommended job card
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 4.dp,
                    hoveredElevation = 4.dp
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "profile picture",
                            modifier = Modifier.size(64.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Row() { Text("Junior android developer") }
                            Row() { Text("Google") }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Row() { Text("Salary") }
                            Row() { Text("($1.5k-2k)/month") }
                        }
                        Column() {
                            Row() { Text("Candidates") }
                            Row() { Text("12") }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        FilterChip(selected = false, onClick = {}, label = {Text("Save")}, shape = CircleShape)
                        FilterChip(selected = false, onClick = {}, label = {Text("Apply Now")}, shape = CircleShape)
                    }
                }
            }
        }
    }
}