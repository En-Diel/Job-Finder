package com.endiel.jobfinder.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.endiel.jobfinder.R
import com.endiel.jobfinder.ui.navigation.Screen
import com.endiel.jobfinder.ui.screens.auth.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Register.route) {
                    inclusive = true
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Create Account", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Sign up to get started with your dashboard",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            Text(
                "Email",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            OutlinedTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                label = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Password",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            OutlinedTextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text("Create a password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))
            uiState.error?.let { errorMessage ->
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (uiState.isVerificationEmailSent) {
                Text(
                    text = "Verification email sent. Please check your inbox",
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp ))
            }
            Button(
                onClick = { viewModel.register() },
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Create Account")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // DIVIDER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // LEFT DIVIDER
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
                // CENTER TEXT
                Text(
                    text = "Or",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // RIGHT DIVIDER
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            // REGISTER WITH
            Row(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google logo",
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "Apple logo",
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "Facebook logo",
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        // SIGN UP
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Already have an account?")
            TextButton(
                onClick = { navController.navigate(Screen.Login.route) },
                enabled = !uiState.isLoading
            ) { Text("Log In") }
        }

        if (uiState.isPasswordResetSent) {
            Text(
                text = "Password reset email sent. Check your inbox and spam folder.",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
        if (uiState.isVerificationEmailSent) {
            Text(
                text = "Verification email sent. Check your inbox and spam folder.",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}