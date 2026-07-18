package com.example.sneakersapp.model.items


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.SubcomposeAsyncImage
import com.example.sneakersapp.R
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.viewmodels.AddReviewModel


@Composable
fun SearchItem(sneaker: Sneaker,onItemClick:(sneaker : Sneaker) -> Unit){

     val viewModel : AddReviewModel = hiltViewModel()
   Card(modifier = Modifier.width(200.dp)
       .height(220.dp)
       .clickable {  onItemClick(sneaker) },
       border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.onPrimaryContainer),
       colors = CardDefaults.cardColors(
           Color(0xFFF5F5F5)
       )) {

       if(sneaker.imageUrl.isEmpty()){
           Image(
               painter = painterResource(id = R.drawable.sneaker_placeholder),
               contentDescription = null,
               modifier = Modifier.fillMaxWidth()
                   .height(120.dp)
           )
       }
       else{
           SubcomposeAsyncImage(
               model = sneaker.imageUrl,
               contentDescription = "Photo of ${sneaker.name}",
               modifier = Modifier.fillMaxWidth()
                   .height(120.dp)
                   .aspectRatio(1.5f)
                   .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
               contentScale = ContentScale.Crop,
               loading = { ShimmerPlaceholder() }
           )
       }

       Spacer(modifier = Modifier.padding(5.dp))

       Text(sneaker.name,
           modifier = Modifier.padding(start = 5.dp),
           maxLines = 1,
           color = MaterialTheme.colorScheme.onPrimary)

       Text(sneaker.brandName,

           modifier = Modifier.padding(start = 5.dp),
           fontWeight = FontWeight.SemiBold,
           maxLines = 1,
           fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary
       )

       Spacer(modifier = Modifier.padding(5.dp))


       Surface(modifier = Modifier.padding(start = 5.dp, end = 5.dp),
           color = MaterialTheme.colorScheme.onPrimaryContainer,
           shape = RoundedCornerShape(12.dp)
       ) {
           Text(
               text = "£ ${sneaker.retailPrice}",
               modifier = Modifier.padding(horizontal = 10.dp),
               fontWeight = FontWeight.SemiBold,
               color = MaterialTheme.colorScheme.onPrimary
           )
       }

   }
}


@Composable
@Preview
fun SearchItem() {

    Card(
        modifier = Modifier.width(130.dp)
            .padding(10.dp)
            .height(200.dp)
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {

        Image(
            painter = painterResource(id = R.drawable.sneaker_placeholder),
            contentDescription = null
        )
        Text(
            "Air Max",
            modifier = Modifier.padding(5.dp)
        )

        Text(
            "A very comfortable shoes",
            modifier = Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 8.sp
        )

        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            "£100",
            modifier = Modifier.padding(start = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }

}

