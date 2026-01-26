package com.example.sneakersapp.presentation

import android.provider.Contacts.Intents.UI
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.items.ReviewItems
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import java.util.ArrayList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(sneakerId : Int, navController: NavController,
                 reviewsViewModel: ReviewsViewModel = hiltViewModel()) {


    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { Text("Reviews") }
            },

            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }
    ) { innerPadding ->
        LaunchedEffect(sneakerId) {
            reviewsViewModel.loadReviewsForSneaker(sneakerId)
        }

        val reviewsState by reviewsViewModel.reviewsState.collectAsState()

        when(val state = reviewsState){
            is UiState.Loading -> {
                CircularProgressIndicator(
                    //  modifier = Modifier.align(Alignment.Center)
                )
            }
            is UiState.Success ->{

                Column(modifier = Modifier.padding(innerPadding)) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {


                        item {
                            state.data.forEach {
                                ReviewItems(it)
                            }

                            Spacer(modifier = Modifier.size(30.dp))
                        }

                    }

                }

            }
            is UiState.Error -> {
                Text(text = "Unexpected error occurred")
            }
        }

    }
}