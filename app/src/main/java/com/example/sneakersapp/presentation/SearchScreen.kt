package com.example.sneakersapp.presentation


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.viewmodels.SneakersViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SearchItem
import com.example.sneakersapp.model.items.SneakerItem
import com.example.sneakersapp.navigation.Screen

@Composable
fun SearchScreen(navController: NavController) {
    val sneakerViewModel = hiltViewModel<SneakersViewModel>()

    val uiState by sneakerViewModel.sneakerState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
        ) {


            when (uiState) {

                is UiState.Loading -> {

                }

                is UiState.Error -> {
                    Log.d("SError", "There was an error")
                }

                is UiState.Success -> {
                    val sneakers = (uiState as UiState.Success<List<Sneaker>>).data
                    items(sneakers) { sneaker ->
                        SearchItem(sneaker)
                    }

                }
            }
        }
    }
}
