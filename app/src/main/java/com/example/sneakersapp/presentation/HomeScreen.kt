package com.example.sneakersapp.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakersapp.model.SneakerItem
import com.example.sneakersapp.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()

    Scaffold(topBar = {
        SearchBar(
            modifier = Modifier
                .padding(20.dp),
            inputField = {
                SearchBarDefaults.InputField(
                    query = "",
                    onQueryChange = { },
                    onSearch = {
                        navController.navigate("search")

                    },
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = { Text("Search") }
                )
            },
            expanded = false,
            onExpandedChange = { navController.navigate("search") },

            ) {
        }
    })


    { innerPadding ->
        val sneakers by viewModel.sneakers.collectAsState()
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                "Recently Viewed",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp)
            )
            LazyRow {
                items(sneakers) { sneaker ->
                    SneakerItem(sneaker)

                }
            }

            Spacer(modifier = Modifier.size(30.dp))

            Text(
                "Top searches this week",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp)
            )

            LazyRow {
                items(sneakers) { sneaker ->
                    SneakerItem(sneaker)

                }
            }
            Spacer(modifier = Modifier.size(30.dp))

            Spacer(modifier = Modifier.size(30.dp))
            Text(
                "Trending",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 20.dp, start = 10.dp)
            )

            LazyRow {
                items(sneakers) { sneaker ->
                    SneakerItem(sneaker)
                    }

                }
            }
        }







    //        TopAppBar(
//            colors = topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                titleContentColor = MaterialTheme.colorScheme.primary,
//            ),
//            title = {
//                Column (modifier = Modifier.fillMaxSize(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally){ Text("Home") }
//            },
//            navigationIcon = { IconButton(onClick = {navController.popBackStack()}){
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "Localized description"
//                )
//            } }
//        )


}