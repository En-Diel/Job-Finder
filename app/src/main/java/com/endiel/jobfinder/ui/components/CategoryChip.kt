package com.endiel.jobfinder.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoryChip(
    category: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {Text(text = category)},
        shape = CircleShape,
        modifier = modifier
    )
}