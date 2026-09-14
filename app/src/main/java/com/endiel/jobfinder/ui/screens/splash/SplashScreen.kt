package com.endiel.jobfinder.ui.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.endiel.jobfinder.ui.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LaunchedEffect(Unit) {
            delay(1500.milliseconds)
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
        Text("Splash Screen")
        Button(
            onClick = { navController.navigate(Screen.Login.route) }
        ) {
            Text("Go to login")
        }
    }
}