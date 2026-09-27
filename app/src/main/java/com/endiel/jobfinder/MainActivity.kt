package com.endiel.jobfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.endiel.jobfinder.ui.navigation.AppNavigation
import com.endiel.jobfinder.ui.theme.JobFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        val startTime = System.currentTimeMillis()
        splashScreen.setKeepOnScreenCondition {
            System.currentTimeMillis() - startTime < 5000
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobFinderTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobFinderTheme {
        Greeting("Android")
    }
}