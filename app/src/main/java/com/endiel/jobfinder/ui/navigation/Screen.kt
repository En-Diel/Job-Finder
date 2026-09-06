package com.endiel.jobfinder.ui.navigation

sealed class Screen(
    val route: String
) {
    data object Splash: Screen("splash")
    data object Login: Screen("login")
    data object Home: Screen("home")
    data object ApplyJob: Screen("apply_job/{jobId}")
    data object SavedJobs: Screen("saved_jobs")
    data object Profile: Screen("profile")
    data object JobDetails: Screen("job_details/{jobId}")
    data object Settings: Screen("settings")
    data object Jobs: Screen("Jobs")
    data object Applications: Screen("applications")
    data object ApplicationDetails: Screen("application_details/{applicationId}")
}