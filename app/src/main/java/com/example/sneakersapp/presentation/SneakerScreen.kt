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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakersapp.R
import com.example.sneakersapp.model.Sneaker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SneakerScreen( sneaker: Sneaker?){

    Scaffold  (topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){ Text(sneaker?.name!!) }
            }
        )
    }
    ){innerPadding ->
        Log.d(sneaker?.name,"SneakerName")
        if(sneaker!=null) {
            Log.d(sneaker.name, "SneakerName")
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                if (sneaker.imageUrl != null) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        painter = painterResource(R.drawable.sneaker_placeholder),
                        contentDescription = sneaker.name
                    )
                } else {

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp),
                        painter = painterResource(R.drawable.sneaker_placeholder),
                        contentDescription = sneaker.name
                    )

                }

                Spacer(modifier = Modifier.size(15.dp))

                Text(text = sneaker.name, fontWeight = FontWeight.Bold,
                    fontSize = 25.sp)

                Spacer(modifier = Modifier.size(15.dp))

                Text(text = sneaker.description!!, fontWeight = FontWeight.Medium,
                    fontSize = 15.sp)

            }
        }

    }

}
