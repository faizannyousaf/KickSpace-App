package com.example.sneakersapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBarInput(
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.Star
                },
                contentDescription = "Rate $i stars",
                tint = if (i <= rating) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    Color.LightGray
                },
                modifier = Modifier
                    .size(32.dp)
                    .padding(start = 8.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}


@Composable
fun RatingBar(
    rating: Double,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) {
                    Icons.Filled.Star
                } else {
                    Icons.Outlined.Star
                },
                contentDescription = null,
                tint = if (i <= rating) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    Color.LightGray
                },
                modifier = Modifier.size(34.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}

