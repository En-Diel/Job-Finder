package com.endiel.jobfinder.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
import com.endiel.jobfinder.ui.screens.jobs.JobsViewModel
import com.endiel.jobfinder.ui.screens.login.LoginScreen
import com.endiel.jobfinder.ui.screens.profile.ProfileScreen
import com.endiel.jobfinder.ui.screens.register.RegisterScreen
import com.endiel.jobfinder.ui.screens.savedjobs.SavedJobsScreen
//import com.endiel.jobfinder.ui.screens.search.SearchScreen
import com.endiel.jobfinder.ui.screens.settings.SettingsScreen
import com.endiel.jobfinder.ui.screens.splash.SplashScreen
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val jobsViewModel: JobsViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        // SPLASH ROUTE
//        composable(Screen.Splash.route) {
//            SplashScreen(navController)
//        }
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
            JobsScreen(
                onBackClick = { navController.popBackStack() },
                navController = navController,
                viewModel = jobsViewModel
            )
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
            val job = jobsViewModel.uiState.value.jobs.find { it.id == jobId }

            if (job !== null) {
                JobDetailsScreen(job = job, onBackClick = {
                    navController.popBackStack()
                }, onApplyClick = {
                    navController.navigate(
                        "apply_job/${job.id}"
                    )
                })
            } else {
                androidx.compose.foundation.layout.Box(
                    modifier = androidx.compose.ui.Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    androidx.compose.foundation.layout.Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
                        Text("Job not found")
                        Button(onClick = { navController.popBackStack() }) { Text("Go back") }
                    }
                }
            }
        }
        // APPLY JOB ROUTE
        composable(Screen.ApplyJob.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId")
            val job = jobsViewModel.uiState.value.jobs.find {
                it.id == jobId
            }
            if (job != null) {
                ApplyJobScreen(
                    job = job,
                    onBackClick = { navController.popBackStack() },
                    onSubmitSuccess = {
                        navController.navigate(
                            Screen.Applications.route
                        ) { popUpTo(Screen.ApplyJob.route) { inclusive = true } }
                    })
            }
        }
        // APPLICATIONS ROUTE
        composable(Screen.Applications.route) {
            ApplicationsScreen(onApplicationClick = { applicationId ->
                navController.navigate("application_details/$applicationId")
            }, onBackClick = { navController.popBackStack() })
        }
        // APPLICATION DETAILS ROUTE
        composable(Screen.ApplicationDetails.route) { backStackEntry ->
            val applicationId = backStackEntry.arguments?.getString("applicationId")

            if (applicationId != null) {
                ApplicationDetailsScreen(
                    applicationId = applicationId, onBackClick = {
                        navController.popBackStack()
                    })
            }
        }
    }
}