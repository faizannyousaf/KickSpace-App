package com.example.sneakersapp.model.items


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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sneakersapp.R
import com.example.sneakersapp.model.entities.Review


@Composable
fun ReviewItems(review: Review){
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
                    , text = "name",
                    fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(5.dp))
            Text(modifier = Modifier.padding(start = 10.dp),
                text = review.comment)
        }

    }

}