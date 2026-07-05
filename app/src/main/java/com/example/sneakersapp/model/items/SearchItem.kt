package com.example.sneakersapp.model.items


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakersapp.model.entities.Sneaker


@Composable
fun SearchItem(sneaker: Sneaker){

   Card(modifier = Modifier.fillMaxWidth()
       .padding(5.dp)
       .height(100.dp)) {
       Text(sneaker.name,
           modifier = Modifier.padding(5.dp))
       Text(sneaker.description,
           modifier = Modifier.padding(start = 5.dp),
           fontWeight = FontWeight.SemiBold,
           fontSize = 8.sp, color = MaterialTheme.colorScheme.secondary
       )

       Spacer(modifier = Modifier.padding(5.dp))
       Text(
           "£${sneaker.retailPrice}",
           modifier = Modifier.padding(start = 10.dp),
           fontWeight = FontWeight.Bold,
           fontSize = 12.sp,
       )
   }
}

//
//@Composable
//@Preview
//fun SearchItem() {
//
//    Card(
//        modifier = Modifier.fillMaxWidth()
//            .padding(10.dp)
//            .height(100.dp)
//    ) {
//        Text(
//            "Air Max",
//            modifier = Modifier.padding(5.dp)
//        )
//
//        Text(
//            "A very comfortable shoes",
//            modifier = Modifier.padding(start = 5.dp),
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 8.sp, color = MaterialTheme.colorScheme.secondary
//        )
//
//        Spacer(modifier = Modifier.padding(10.dp))
//        Text(
//            "£100",
//            modifier = Modifier.padding(start = 5.dp),
//            fontWeight = FontWeight.Bold,
//            fontSize = 12.sp
//        )
//    }
//}