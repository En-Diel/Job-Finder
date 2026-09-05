package com.endiel.jobfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.endiel.jobfinder.data.repository.sampleJobs
import com.endiel.jobfinder.ui.screens.applications.ApplicationsScreen
import com.endiel.jobfinder.ui.screens.applyjob.ApplyJobScreen
import com.endiel.jobfinder.ui.screens.home.HomeScreen
import com.endiel.jobfinder.ui.screens.jobdetails.JobDetailsScreen
import com.endiel.jobfinder.ui.screens.jobs.JobsScreen
import com.endiel.jobfinder.ui.screens.login.LoginScreen
import com.endiel.jobfinder.ui.screens.profile.ProfileScreen
import com.endiel.jobfinder.ui.screens.savedjobs.SavedJobsScreen
//import com.endiel.jobfinder.ui.screens.search.SearchScreen
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
        composable(Screen.SavedJobs.route) {
            SavedJobsScreen(onBackClick = { navController.popBackStack() }, navController)
        }
        composable(Screen.Profile.route) { backStackEntry ->

            ProfileScreen(onBackClick = { navController.popBackStack() }, navController)
        }
        composable(Screen.Jobs.route) {
            JobsScreen(onBackClick = { navController.popBackStack() }, navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController)
        }
        composable("job_details/{jobId}") { backStackEntry ->

            val jobId = backStackEntry.arguments?.getString("jobId")
            //Text(text = "JOB DETAILS ID: $jobId") // Debugging the route
            val job = sampleJobs.find { it.id == jobId }

            if (job !== null) {
                JobDetailsScreen(
                    job = job,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onApplyClick = {
                        navController.navigate(
                            "apply_job/${job.id}"
                        )
                    }
                )
            }
        }
        composable(Screen.ApplyJob.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            ApplyJobScreen(jobId = jobId)
        }
        composable(Screen.Applications.route) {
            ApplicationsScreen(onBackClick = { navController.popBackStack() })
        }
    }
}