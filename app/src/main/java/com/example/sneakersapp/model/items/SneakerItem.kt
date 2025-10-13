package com.example.sneakersapp.model.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakersapp.R
import com.example.sneakersapp.model.entities.Sneaker

@Composable
fun SneakerItem(sneaker: Sneaker, onItemClick:(sneaker : Sneaker) -> Unit){
 //   val navController : NavController
    Column (modifier = Modifier.fillMaxWidth()
        .clickable {  onItemClick(sneaker) }
        .padding(8.dp)) {
        if(sneaker.imageUrl != null){
            Image(
                painter = painterResource(R.drawable.sneaker_placeholder),
                contentDescription = sneaker.name,
                modifier = Modifier.size(200.dp)
            )
        }
       else{
           Image(painter = painterResource(R.drawable.sneaker_placeholder),
               contentDescription = sneaker.name,
               modifier = Modifier.size(200.dp))
        }
        Spacer(modifier = Modifier.size(10.dp))
           Text(sneaker.name!!,
               fontWeight = FontWeight.Bold,
               fontSize = 20.sp)
            Text(sneaker.releaseYear!!,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

    }
}