

package com.example.sneakersapp.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.presentation.ShimmerPlaceholder
import com.example.sneakersapp.viewmodels.AddReviewModel
import com.example.sneakersapp.viewmodels.ReviewsViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel

@ExperimentalMaterial3Api
@Composable
fun AddReviewScreen(sneakerID : Int, navController : NavController){

    val sneakerViewModel = hiltViewModel<SneakersViewModel>()
    val uiState by sneakerViewModel.sneakerState.collectAsState()
     val reviewViewModel : ReviewsViewModel = hiltViewModel()

   Scaffold(topBar = {
       TopAppBar(title = {
         Text("Write A Review",
             color = MaterialTheme.colorScheme.onPrimary)
       },
           navigationIcon = {
               IconButton(onClick = {navController.popBackStack()}) {
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
               onClick ={ reviewViewModel.insertReviews(Review(0,reviewViewModel.comment,
                   sneakerID,reviewViewModel.selectedRating,reviewViewModel.getUserName()))
                        navController.popBackStack()},
           ) {
               Text("Add Review")
           }
       }

   ) { innerPadding->


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

               Column(Modifier.padding(innerPadding)) {
                   Spacer(modifier = Modifier.size(20.dp))
                   Row {
                       SubcomposeAsyncImage(
                           model = sneaker?.imageUrl,
                           contentDescription = "Photo of ${sneaker?.name}",
                           modifier = Modifier.width(100.dp)
                               .height(90.dp),
                           loading = { ShimmerPlaceholder() }
                       )

                       Column {
                           Text(
                               sneaker?.name!!,
                               fontWeight = FontWeight.SemiBold,
                               modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                           )


                           Text(
                               sneaker.brandName,
                               fontWeight = FontWeight.Medium,
                               fontSize = 14.sp,
                               color = MaterialTheme.colorScheme.secondary,
                               modifier = Modifier.padding(start = 10.dp, top = 3.dp)
                           )
                       }
                   }


                   Spacer(modifier = Modifier.size(20.dp))

                   HorizontalDivider(
                       modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp),
                       thickness = 1.dp,
                       color = Color.LightGray
                   )

                   Text(
                       "Your Rating",
                       fontWeight = FontWeight.SemiBold,
                       fontSize = 20.sp,
                       modifier = Modifier.padding(start = 10.dp, top = 3.dp)
                   )

                   Spacer(modifier = Modifier.size(10.dp))

                   RatingBarInput(
                       rating = reviewViewModel.selectedRating,
                       onRatingChanged = { reviewViewModel.selectedRating = it })

                   Spacer(modifier = Modifier.size(10.dp))
                   Text(
                       "Tap to rate",
                       fontWeight = FontWeight.SemiBold,
                       color = MaterialTheme.colorScheme.secondary,
                       modifier = Modifier.padding(start = 10.dp, top = 3.dp)
                   )

                   Spacer(modifier = Modifier.size(10.dp))

                   Text(
                       "Your Review",
                       fontWeight = FontWeight.SemiBold,
                       fontSize = 20.sp,
                       modifier = Modifier.padding(start = 10.dp, top = 3.dp)
                   )

                   Spacer(modifier = Modifier.size(10.dp))

                   OutlinedTextField(
                       modifier = Modifier.fillMaxWidth()
                           .height(200.dp)
                           .padding(start = 10.dp, end = 10.dp),
                       value = reviewViewModel.comment,
                       onValueChange = { reviewViewModel.comment = it },
                       placeholder = { Text("Share your thoughts about this sneaker") },
                       maxLines = 1,
                       colors = getTextFieldColors()
                   )
               }


           }
       }

   }

}