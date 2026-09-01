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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.endiel.jobfinder.R
import com.endiel.jobfinder.ui.components.BottomNavBar
import com.endiel.jobfinder.ui.components.CategoryChip
import com.endiel.jobfinder.ui.components.JobSearchBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    // We'll use this when we create a viewModel
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    // This data will be coming from API later
    val categories = listOf(
        "All",
        "Technology",
        "Design",
        "Finance",
        "Marketing",
        "Sales",
        "Engineering"
    )

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
            // WELCOME MESSAGE
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
            // SEARCH BAR
            JobSearchBar(
                searchQuery, onQueryChange = { searchQuery = it }
            )
            Spacer(modifier = Modifier.height(8.dp))
            // CATEGORY FILTER CHIPS
            Text(
                "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(
                        category = category,
                        selected = selectedCategory == category,
                        onClick = {
                            selectedCategory = category
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Recommended jobs",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // RECOMMENDED JOBS CARD
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 4.dp,
                    hoveredElevation = 4.dp
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Row() { Text("Salary") }
                            Row() { Text("($200k-300k)/year") }
                        }
                        Column() {
                            Row() { Text("Candidates") }
                            Row() { Text("12") }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        FilterChip(selected = false, onClick = {}, label = {Text("Save")}, shape = CircleShape)
                        FilterChip(selected = false, onClick = {}, label = {Text("Apply Now")}, shape = CircleShape)
                    }
                }
            }
            // CARD 2
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 4.dp,
                    hoveredElevation = 4.dp
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "profile picture",
                            modifier = Modifier.size(64.dp).clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Row() { Text("Senior backend developer") }
                            Row() { Text("Meta") }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Row() { Text("Salary") }
                            Row() { Text("($400k-600k)/year") }
                        }
                        Column() {
                            Row() { Text("Candidates") }
                            Row() { Text("34") }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FlowRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        FilterChip(selected = false, onClick = {}, label = {Text("Save")}, shape = CircleShape)
                        FilterChip(selected = false, onClick = {}, label = {Text("Apply Now")}, shape = CircleShape)
                    }
                }
            }
        }
    }
}