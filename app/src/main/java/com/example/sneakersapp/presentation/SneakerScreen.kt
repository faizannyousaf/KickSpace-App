package com.example.sneakersapp.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakersapp.R
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerScreen(sneakerID : Int){


    val reviewViewModel = hiltViewModel<ReviewsViewModel>()

    val sneakerViewModel = hiltViewModel<SneakersViewModel>()

    val uiState by sneakerViewModel.sneakerState.collectAsState()



    Scaffold  (topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {

                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){ Text("No name") }
            }
        )
    }
    ) { innerPadding ->


        when (uiState) {

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Log.d("SError", "There was an error")
            }

            is UiState.Success -> {
                val addReview by reviewViewModel.review.observeAsState("")
                val sneakers = (uiState as UiState.Success<List<Sneaker>>).data
                val sneaker = sneakers.find { it.id == sneakerID }

                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        painter = painterResource(R.drawable.sneaker_placeholder),
                        contentDescription = sneaker?.name
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Text(modifier = Modifier.padding(start = 10.dp),
                        text = sneaker?.name!!, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Text( modifier = Modifier.padding(start = 10.dp),
                        text = sneaker.description, fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.size(15.dp))


                    OutlinedTextField( modifier = Modifier.fillMaxWidth()
                        .padding(10.dp),
                        value = addReview,
                        onValueChange = {
                            reviewViewModel.onReviewChange(it)
                        },
                        label =
                            {
                                Text("Your review")
                            }
                    )

                    Button(onClick = { reviewViewModel.insertReviews(Review(1,addReview,4,1,2,))},
                        modifier = Modifier.padding(start = 10.dp)) {
                        Text("Add Review")
                    }


                }
            }
        }
    }
}
