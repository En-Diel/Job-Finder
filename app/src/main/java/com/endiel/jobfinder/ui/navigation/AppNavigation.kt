package com.endiel.jobfinder.ui.navigation

import android.window.SplashScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            // SplashScreen()
        }
        composable(Screen.Login.route) {
            // LoginScreen()
        }
        composable(Screen.Home.route) {
            //HomeScreen()
        }
        composable(Screen.Search.route) {
            // SearchScreen()
        }
        composable(Screen.SavedJobs.route) {
            // SavedJobsScreen()
        }
        composable(Screen.Profile.route) {
            // ProfileScreen()
        }
        composable(Screen.JobDetails.route) {
            // JobDetailsScreen()
        }
    }
}