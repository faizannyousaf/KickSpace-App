@file:Suppress("UNUSED_EXPRESSION")

package com.example.sneakersapp.presentation

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.navigation.Screen
import com.example.sneakersapp.ui.theme.RatingBar
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerScreen(sneakerID : Int, navController: NavController){


    val reviewViewModel = hiltViewModel<ReviewsViewModel>()

    val sneakerViewModel = hiltViewModel<SneakersViewModel>()

    val uiState by sneakerViewModel.sneakerState.collectAsState()


    Scaffold(
        topBar =
        {
            TopAppBar(
            title = {"Street Essential"},
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
},
        bottomBar = {
            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary, disabledContainerColor
                    = MaterialTheme.colorScheme.surface,
                    disabledContentColor = MaterialTheme.colorScheme.surface,
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
                    .height(60.dp),
                onClick = { navController.navigate(Screen.AddReview.addReviewRoute(sneakerID)) },
            ) {
                Text("Write a Reviews")
            }
        }

    )
    { innerPadding ->


        when (uiState) {

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Error -> {
                Log.d("SError", "There was an error")
            }

            is UiState.Success -> {
                val sneakers = (uiState as UiState.Success<List<Sneaker>>).data
                val sneaker = sneakers.find { it.id == sneakerID }
                LaunchedEffect(sneakerID) {
                    reviewViewModel.loadReviewsForSneaker(sneakerID)
                }
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .fillMaxHeight()
                ) {
                    SubcomposeAsyncImage(
                        model = sneaker?.imageUrl,
                        contentDescription = "Photo of ${sneaker?.name}",
                        modifier = Modifier.fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop,
                        loading = {ShimmerPlaceholder() }
                    )

                    Spacer(modifier = Modifier.size(15.dp))

                    Text(modifier = Modifier.padding(start = 10.dp),
                        text = sneaker?.name!!, fontWeight = FontWeight.Bold,
                        fontSize = 25.sp, color = MaterialTheme.colorScheme.onPrimary
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    Text( modifier = Modifier.padding(start = 10.dp),
                        text = sneaker.brandName, fontWeight = FontWeight.Medium,
                        fontSize = 18.sp, color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.size(5.dp))

                    Surface(modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = "£${sneaker.retailPrice}",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        "Description",
                                modifier = Modifier.padding(start = 10.dp),
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(modifier = Modifier.padding(start = 10.dp, end = 5.dp),
                        text = sneaker.description, fontWeight = FontWeight.Medium,
                        fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "Reviews",
                        modifier = Modifier.padding(start = 10.dp),
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary
                    )

                    Row {

                        LaunchedEffect(sneakerID) {
                            reviewViewModel.getReviewsCount(sneakerID)
                        }
                        LaunchedEffect(sneakerID) {
                            reviewViewModel.getAverageRating(sneakerID)
                        }

                        RatingBar(reviewViewModel.ratingAverage.toInt() , modifier = Modifier)

                        Text(modifier = Modifier.padding(start = 5.dp,top = 5.dp),

                            text = "(${reviewViewModel.reviewCount} reviews)", fontWeight = FontWeight.Medium,
                            fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Text(
                        "See all reviews ->",
                        modifier = Modifier.padding(start = 10.dp)
                            .clickable {
                                navController.navigate(Screen.Reviews.reviewsRoute(sneakerID))
                            },
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimaryContainer
                    )


                    if(reviewViewModel.review.isEmpty()){
                        Log.d("Error","Need Input")
                    }
                    else{
                        Button(onClick = { reviewViewModel.insertReviews(Review
                            (0,reviewViewModel.review,4,0))
                            reviewViewModel.review = ""},
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary,
                                contentColor = Color.White),
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Text("Add Review")
                        }
                    }


                    Spacer(modifier = Modifier.size(15.dp))
                    //LoadReviews(reviewViewModel,sneakerID, navController)
                }

            }
        }
    }
}


@Composable
fun ShimmerPlaceholder() {
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = listOf(Color.LightGray.copy(alpha = 0.6f), Color.LightGray.copy(alpha = 0.2f), Color.LightGray.copy(alpha = 0.6f)),
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    Box(modifier = Modifier.fillMaxSize().background(brush))
}

