package com.endiel.jobfinder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.endiel.jobfinder.R

// Define our custom font family
val CustomFont = FontFamily(
    Font(
        R.font.montserrat_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.montserrat_medium,
        FontWeight.Medium
    ),
    Font(
        R.font.montserrat_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.montserrat_italic,
        FontWeight.Normal, FontStyle.Italic
    ),
    Font(
        R.font.montserrat_thin,
        FontWeight.Thin
    ),
    Font(
        R.font.montserrat_light,
        FontWeight.Light
    )
)
/*
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = CustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */

 */

val AppTypography = Typography(

    // Display
    displayLarge = TextStyle(
        fontFamily = CustomFont,
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 64.sp
    ),

    displayMedium = TextStyle(
        fontFamily = CustomFont,
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 52.sp
    ),

    displaySmall = TextStyle(
        fontFamily = CustomFont,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 44.sp
    ),

    // Headlines
    headlineLarge = TextStyle(
        fontFamily = CustomFont,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 40.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = CustomFont,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 36.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = CustomFont,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 32.sp
    ),

    // Titles
    titleLarge = TextStyle(
        fontFamily = CustomFont,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp
    ),

    titleMedium = TextStyle(
        fontFamily = CustomFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    ),

    titleSmall = TextStyle(
        fontFamily = CustomFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = CustomFont,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = CustomFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    ),

    bodySmall = TextStyle(
        fontFamily = CustomFont,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    ),

    // Labels
    labelLarge = TextStyle(
        fontFamily = CustomFont,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp
    ),

    labelMedium = TextStyle(
        fontFamily = CustomFont,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp
    ),

    labelSmall = TextStyle(
        fontFamily = CustomFont,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 16.sp
    )
)