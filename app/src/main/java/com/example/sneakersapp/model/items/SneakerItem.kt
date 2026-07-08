package com.example.sneakersapp.model.items

import android.media.Image
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.SubcomposeAsyncImage
import com.example.sneakersapp.R
import com.example.sneakersapp.model.entities.Sneaker

@Composable
fun SneakerItem(sneaker: Sneaker, onItemClick:(sneaker : Sneaker) -> Unit){
    Column (modifier = Modifier.fillMaxWidth()
        .clickable {  onItemClick(sneaker) }
        .padding(8.dp)) {
        if(sneaker.imageUrl.isEmpty()){
            Image(
                painter = painterResource(id = R.drawable.sneaker_placeholder),
                contentDescription = null,
                modifier = Modifier.width(300.dp)
                    .height(200.dp)
            )
        }
        else{
            SubcomposeAsyncImage(
                model = sneaker.imageUrl,
                contentDescription = "Photo of ${sneaker.name}",
                modifier = Modifier.width(300.dp)
                    .height(200.dp)
                    .aspectRatio(1.5f)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop,
                loading = { ShimmerPlaceholder() }
            )
        }


        Spacer(modifier = Modifier.size(10.dp))
           Text(sneaker.name.take(10),
               fontWeight = FontWeight.Bold,
               fontSize = 20.sp)
            Text(sneaker.brandName,
                fontSize = 20.sp
            )

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

