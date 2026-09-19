package com.endiel.jobfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.endiel.jobfinder.data.repository.sampleJobs
import com.endiel.jobfinder.model.UserProfile
import com.endiel.jobfinder.ui.screens.applications.ApplicationsScreen
import com.endiel.jobfinder.ui.screens.applications.ApplicationsViewModel
import com.endiel.jobfinder.ui.screens.applyjob.ApplyJobScreen
import com.endiel.jobfinder.ui.screens.home.HomeScreen
import com.endiel.jobfinder.ui.screens.jobdetails.ApplicationDetailsScreen
import com.endiel.jobfinder.ui.screens.jobdetails.JobDetailsScreen
import com.endiel.jobfinder.ui.screens.jobs.JobsScreen
import com.endiel.jobfinder.ui.screens.login.LoginScreen
import com.endiel.jobfinder.ui.screens.profile.ProfileScreen
import com.endiel.jobfinder.ui.screens.register.RegisterScreen
import com.endiel.jobfinder.ui.screens.savedjobs.SavedJobsScreen
//import com.endiel.jobfinder.ui.screens.search.SearchScreen
import com.endiel.jobfinder.ui.screens.settings.SettingsScreen
import com.endiel.jobfinder.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        // SPLASH ROUTE
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        // LOGIN ROUTE
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        // REGISTER
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        // HOME ROUTE
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        // SAVED JOBS ROUTE
        composable(Screen.SavedJobs.route) {
            SavedJobsScreen(onBackClick = { navController.popBackStack() }, navController)
        }
        // PROFILE ROUTE
        composable(Screen.Profile.route) { backStackEntry ->
            ProfileScreen(
                navController = navController,
                onBackClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = false // Keep the destination specified in the popUpTo
                            // in the back stack
                        }
                        launchSingleTop = true // Prevents duplicate copies of the same
                        // destination in the backstack
                    }
                },
                onSavedJobsClick = { navController.navigate(Screen.SavedJobs.route) },
                onApplicationsClick = { navController.navigate(Screen.Applications.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) },
            )
        }
        // JOBS ROUTE
        composable(Screen.Jobs.route) {
            JobsScreen(onBackClick = { navController.popBackStack() }, navController)
        }
        // SETTINGS ROUTE
        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }, navController
            )
        }
        // JOB DETAILS ROUTE
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
        // APPLY JOB ROUTE
        composable(Screen.ApplyJob.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            ApplyJobScreen(
                onBackClick = { navController.popBackStack() },
                jobId = jobId,
                onSubmitSuccess = {
                    navController.navigate(
                        Screen.Applications.route
                    ) { popUpTo(Screen.ApplyJob.route) { inclusive = true } }
                })
        }
        // APPLICATIONS ROUTE
        composable(Screen.Applications.route) {
            ApplicationsScreen(
                onApplicationClick = { applicationId ->
                    navController.navigate("application_details/$applicationId")
                },
                onBackClick = { navController.popBackStack() })
        }
        // APPLICATION DETAILS ROUTE
        composable(Screen.ApplicationDetails.route) { backStackEntry ->
            val applicationId = backStackEntry.arguments?.getString("applicationId")

            if (applicationId != null) {
                ApplicationDetailsScreen(
                    applicationId = applicationId,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}