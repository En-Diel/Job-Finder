package com.endiel.jobfinder.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Cases
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    data object Home: BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Jobs: BottomNavItem(
        route = Screen.Jobs.route,
        title = "Jobs",
        icon = Icons.Default.Cases
    )
    data object Saved: BottomNavItem(
        route = Screen.SavedJobs.route,
        title = "Saved",
        icon = Icons.Default.Bookmark
    )
    data object Profile: BottomNavItem(
        route = Screen.Profile.route,
        title = "Profile",
        icon = Icons.Default.Person2
    )
}