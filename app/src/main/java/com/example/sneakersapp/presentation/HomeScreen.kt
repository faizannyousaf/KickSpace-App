package com.example.sneakersapp.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SneakerItem
import com.example.sneakersapp.navigation.Screen
import com.example.sneakersapp.viewmodels.SneakersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sneakerViewModel = hiltViewModel<SneakersViewModel>()

    val uiState by sneakerViewModel.sneakerState.collectAsState()
Box {
    Scaffold(topBar = {
        SearchBar(
            modifier = Modifier.padding(20.dp),
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {
                navController.navigate("search")
            },
            placeholder = { Text("Search for sneakers") }
        ) {
            // Empty - we're navigating away
        }

    })


    { innerPadding ->

        Spacer(modifier = Modifier.size(30.dp))

        when(uiState){

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Log.d("SError","There was an error")
            }

            is UiState.Success -> {
                val sneakers = (uiState as UiState.Success<List<Sneaker>>).data
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {


                    item {
                        ListCategory(
                            "Recently Viewed",
                            sneakers, navController
                        )
                        Spacer(modifier = Modifier.size(30.dp))
                    }

                    item {
                        ListCategory(
                            "Top Searches this week",
                            sneakers, navController
                        )

                        Spacer(modifier = Modifier.size(30.dp))
                    }
                    item {
                        ListCategory(
                            "Trending shoes",
                            sneakers, navController
                        )
                    }
                }
            }
        }
        }

}
}

@Composable
fun ListCategory(categoryName : String, sneakers : List<Sneaker>, navController: NavController){


    Text(
        categoryName,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 20.dp, start = 10.dp)
    )
    LazyRow {
        items(sneakers) { sneaker ->
            SneakerItem(sneaker, onItemClick = { selectedSneaker->
//
                Log.d("SneakerIddF",selectedSneaker.id.toString())
                navController.navigate(Screen.Sneaker.sneakerDetailRoute(selectedSneaker.id!!))
            })
        }

    }
}

