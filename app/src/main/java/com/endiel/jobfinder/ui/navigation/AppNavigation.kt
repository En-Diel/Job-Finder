package com.endiel.jobfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.endiel.jobfinder.ui.screens.home.HomeScreen
import com.endiel.jobfinder.ui.screens.jobdetails.JobDetailsScreen
import com.endiel.jobfinder.ui.screens.jobs.JobsScreen
import com.endiel.jobfinder.ui.screens.login.LoginScreen
import com.endiel.jobfinder.ui.screens.profile.ProfileScreen
import com.endiel.jobfinder.ui.screens.savedjobs.SavedJobsScreen
import com.endiel.jobfinder.ui.screens.search.SearchScreen
import com.endiel.jobfinder.ui.screens.settings.SettingsScreen
import com.endiel.jobfinder.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
        composable(Screen.SavedJobs.route) {
            SavedJobsScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.JobDetails.route) {
            JobDetailsScreen()
        }
        composable(Screen.Jobs.route) {
            JobsScreen(navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
    }
}