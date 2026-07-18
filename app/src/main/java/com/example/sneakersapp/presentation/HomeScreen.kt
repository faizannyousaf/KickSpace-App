package com.example.sneakersapp.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.R
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SneakerItem
import com.example.sneakersapp.navigation.Screen
import com.example.sneakersapp.ui.theme.getTextFieldColors
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sneakerViewModel = hiltViewModel<SneakersViewModel>()
    val reviewViewModel = hiltViewModel<ReviewsViewModel>()

    val uiState by sneakerViewModel.sneakerState.collectAsState()
Box {
    Scaffold(
//        topBar = {
//        SearchBar(
//            modifier = Modifier.padding(10.dp),
//            query = "",
//            onQueryChange = {},
//            onSearch = {},
//            active = false,
//            onActiveChange = {
//                navController.navigate("search")
//            },
//            placeholder = { Text("Search for sneakers") },
//            colors = SearchBarDefaults.colors(MaterialTheme.colorScheme.surfaceVariant)
//        ) {
//            // Empty - we're navigating away
//        }
//
//    }
    )


    { innerPadding ->

        Column(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
            .padding(innerPadding)
            .background(color = MaterialTheme.colorScheme.surface)) {

            Row (modifier = Modifier.padding(top =15.dp, start = 10.dp)){
                Image(
                    painter = painterResource(id = R.drawable.kickspace_logo),
                    contentDescription = "KickSpace Logo",
                    modifier = Modifier.size(60.dp)
                )

                Spacer(modifier = Modifier.padding(start = 3.dp))
                Text("Hi, ${reviewViewModel.getUserName()}",
                    modifier = Modifier.padding(start = 5.dp, top = 20.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )

            }

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .clickable {
                        navController.navigate("search")
                    },
                value = "",
                onValueChange = {  },
                placeholder = { Text("Search Sneakers") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFF999999)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(50.dp)
            )



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
}

@Composable
fun ListCategory(categoryName : String, sneakers : List<Sneaker>, navController: NavController){


    Row {
        Text(
        categoryName,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 20.dp, start = 10.dp)
    )
        Spacer(modifier = Modifier.weight(1f))  // Pushes "See all" to the right
        Text(
            "See all ->",
            modifier = Modifier.padding(start = 10.dp, top = 20.dp, end = 10.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
    LazyRow {
        items(sneakers) { sneaker ->
            SneakerItem(sneaker, onItemClick = { selectedSneaker->
//
                Log.d("SneakerIddF",selectedSneaker.id.toString())
                navController.navigate(Screen.Sneaker.sneakerDetailRoute(selectedSneaker.id))
            })
        }

    }
}

