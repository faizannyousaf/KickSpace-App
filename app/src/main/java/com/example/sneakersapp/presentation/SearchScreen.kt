package com.example.sneakersapp.presentation


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.viewmodels.SneakersViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SearchItem
import com.example.sneakersapp.ui.theme.TopBarBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    val sneakerViewModel = hiltViewModel<SneakersViewModel>()

    val uiState by sneakerViewModel.sneakerState.collectAsState()


    Scaffold(topBar = {
        SearchBar(
            modifier = Modifier.padding(20.dp),
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {
            },
            placeholder = { Text("Search for sneakers") },
            colors = SearchBarDefaults.colors(
                containerColor = Color(0xFF1A1A1A),        // Dark background
                dividerColor = Color(0xFF2A2A2A),           // Divider line
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = Color(0xFFFFFFFF),   // White text when typing
                    unfocusedTextColor = Color(0xFFFFFFFF), // White text
                    focusedPlaceholderColor = Color(0xFF999999), // Grey placeholder
                    unfocusedPlaceholderColor = Color(0xFF999999), // Grey placeholder
                    cursorColor = Color(0xFFFF385C)         // Coral red cursor
                )
            )
        ) {
            // Empty - we're navigating away
        }

    }){innerPadding->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
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

}
