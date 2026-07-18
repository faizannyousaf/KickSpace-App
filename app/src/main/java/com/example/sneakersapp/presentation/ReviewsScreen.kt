@file:Suppress("UNREACHABLE_CODE")

package com.example.sneakersapp.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.items.ReviewItems
import com.example.sneakersapp.ui.theme.RatingBar
import com.example.sneakersapp.viewmodels.ReviewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(sneakerId : Int, navController: NavController,
                 reviewsViewModel: ReviewsViewModel = hiltViewModel()) {


    Scaffold(topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
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
                        modifier = Modifier.size(30.dp),
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
                    Card (modifier = Modifier.padding(10.dp)
                        .height(110.dp)
                        .fillMaxWidth(),
                        colors = CardColors(
                            MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.surface,
                            disabledContentColor = MaterialTheme.colorScheme.surface,
                        )
                    ) {
                        LaunchedEffect(sneakerId) {
                            reviewsViewModel.getAverageRating(sneakerId)
                        }

                        LaunchedEffect(sneakerId) {
                            reviewsViewModel.getReviewsCount(sneakerId)
                        }
                        Column {
                            Row{
                                Text(reviewsViewModel.ratingAverage.toString(),
                                    modifier = Modifier.padding(10.dp),
                                    fontSize = 40.sp,
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold

                                )
                                RatingBar(reviewsViewModel.ratingAverage.toInt(), modifier = Modifier.padding(top = 10.dp))
                            }
                            Text( modifier = Modifier.padding(start = 10.dp),
                                text = "Based on ${reviewsViewModel.reviewCount} reviews", fontWeight = FontWeight.Medium,
                                fontSize = 18.sp, color = MaterialTheme.colorScheme.secondary
                            )
                        }

                    }
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