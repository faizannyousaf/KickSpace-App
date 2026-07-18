package com.example.sneakersapp.model.items


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sneakersapp.R
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.ui.theme.RatingBar
import com.example.sneakersapp.viewmodels.ReviewsViewModel


@Composable
fun ReviewItems(review: Review){
    val reviewViewModel = hiltViewModel<ReviewsViewModel>()

    Card(
        modifier = Modifier.fillMaxWidth()
            .height(175.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Column {
            Row (modifier = Modifier.height(80.dp)) {
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
                    , text = review.userName,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.weight(1f))

                RatingBar(review.rating , modifier = Modifier.padding(end = 10.dp)
                    .padding(top = 5.dp))
            }
            Spacer(modifier = Modifier.size(5.dp))

            Text(modifier = Modifier.padding(start = 10.dp),
                text = review.comment,
                fontSize = 18.sp)

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }

    }

}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun ReviewItems(){


    Card(
        modifier = Modifier.fillMaxWidth()
            .height(175.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Column {
            Row(modifier = Modifier.height(80.dp)) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.user_placeholder),
                    contentDescription = "user_picture"
                )

                Text(modifier = Modifier.padding(top = 17.dp)
                    , text = "Faizan",
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))

                RatingBar(0 , modifier = Modifier.padding(end = 10.dp)
                    .padding(top = 5.dp))
            }

            Text(modifier = Modifier.padding(start = 10.dp),
                text = "Nice")
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 10.dp),
            thickness = 1.dp,
            color = Color.LightGray
        )

    }

}