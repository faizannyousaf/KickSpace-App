package com.example.sneakersapp.presentation



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SearchItem
import com.example.sneakersapp.navigation.Screen
import com.example.sneakersapp.ui.theme.getTextFieldColors
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel


@Composable
fun SearchScreen (navController: NavController) {
    var active by rememberSaveable { mutableStateOf(false) }


    val viewModel: SneakersViewModel = hiltViewModel()
    val reviewViewModel: ReviewsViewModel = hiltViewModel()

    val uiState by viewModel.sneakerState.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                value = reviewViewModel.userQuery,
                onValueChange = { reviewViewModel.userQuery= it },
                placeholder = { Text("Search Sneakers") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color(0xFF999999)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(50.dp),

                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.size(10.dp))


            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    //
                }

                is UiState.Success -> {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val sneakers = (uiState as UiState.Success<List<Sneaker>>).data

                        items(reviewViewModel.filteredShoes(sneakers)){sneaker->

                                SearchItem(sneaker = sneaker, onItemClick = { selectedSneaker->
                                    navController.navigate(Screen.Sneaker.sneakerDetailRoute(selectedSneaker.id))
                                })
                        }
                    }

                }
            }

        }
    }
    }





