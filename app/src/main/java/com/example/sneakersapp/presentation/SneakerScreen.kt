package com.example.sneakersapp.presentation

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.sneakersapp.R
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.navigation.Screen
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerScreen(sneakerID : Int, navController: NavController){


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
                    horizontalAlignment = Alignment.CenterHorizontally){ Text("") }
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
                ) {
                    SubcomposeAsyncImage(
                        model = sneaker?.imageUrl,
                        contentDescription = "Photo of ${sneaker?.name}",
                        modifier = Modifier.fillMaxWidth()
                            .height(300.dp)
                            .aspectRatio(1.5f)
                            .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                        contentScale = ContentScale.Crop,
                        loading = {ShimmerPlaceholder() }
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
                        value = reviewViewModel.review,
                        onValueChange = {
                            reviewViewModel.review = it
                        },
                        label =
                            {
                                Text("Your review")
                            }
                    )

                    Button(onClick = { reviewViewModel.insertReviews(Review
                        (0,reviewViewModel.review,4, sneaker.id))
                        reviewViewModel.review = ""},
                        modifier = Modifier.padding(start = 10.dp)) {
                        Text("Add Review")
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    LoadReviews(reviewViewModel,sneakerID, navController)
                }

            }
        }
    }
}

@Composable
fun LoadReviews(reviewsViewModel: ReviewsViewModel, sneakerID: Int,
                navController: NavController){

    val reviewsState by reviewsViewModel.reviewsState.collectAsState()

   when(val state = reviewsState){
       is UiState.Loading -> {
           CircularProgressIndicator(
               modifier = Modifier.size(20.dp),
               color = MaterialTheme.colorScheme.onPrimary
           )
       }

       is UiState.Success -> {
           if(state.data.isNotEmpty())
               ReviewsUI(state.data,reviewsViewModel.getUserEmail(), navController,sneakerID)
       }

       is UiState.Error -> TODO()
   }


}

@Composable
fun ReviewsUI(reviewsList : List<Review>, userEmail : String,
              navController: NavController, sneakerID: Int){

    Card(
        modifier = Modifier.fillMaxWidth()
            .height(175.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        )
    ) {
        Column {
            Row {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.user_placeholder),
                    contentDescription = "user_picture"
                )

                Text(modifier = Modifier.padding(top = 15.dp)
                    , text = "@${userEmail.take(6)}",
                    fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(modifier = Modifier.padding(start = 10.dp),
                text = reviewsList.last().comment)
        }

        Spacer(modifier = Modifier.size(5.dp))

        Text(modifier = Modifier.clickable {
            navController.navigate(Screen.Reviews.reviewsRoute(sneakerID))

        },
            fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, text = "See more..")

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

