package com.example.sneakersapp.model.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sneakersapp.model.entities.Sneaker

@Composable
fun SearchItem(sneaker: Sneaker){

    Row(modifier = Modifier.fillMaxWidth()
        .height(100.dp)
        .padding(10.dp)) {
        Text(sneaker.name)
    }
}