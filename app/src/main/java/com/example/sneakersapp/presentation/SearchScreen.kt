package com.example.sneakersapp.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SearchItem
import com.example.sneakersapp.viewmodels.SneakersViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen (navController: NavController){
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }


    val viewModel : SneakersViewModel = hiltViewModel()

    val uiState by viewModel.sneakerState.collectAsState()

    Scaffold { innerPadding ->
        Column (modifier = Modifier.fillMaxSize()
            .padding(innerPadding)){


            Surface(modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF1A1A1A) ) {

                OutlinedTextField(modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                    value = query,
                    onValueChange = {query = it},
                    label = { Text("Search Sneakers") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = Color(0xFF999999)
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color(0xFFFFFFFF),
                        unfocusedTextColor = Color(0xFFFFFFFF),
                        focusedContainerColor = Color(0xFF2A2A2A),
                        unfocusedContainerColor = Color(0xFF2A2A2A),
                        cursorColor = Color(0xFFFF385C),
                        focusedBorderColor = Color(0xFFFF385C),
                        unfocusedBorderColor = Color(0xFF333333),
                        focusedPlaceholderColor = Color(0xFF999999),
                        unfocusedPlaceholderColor = Color(0xFF999999),
                        focusedLeadingIconColor = Color(0xFFFF385C),
                        unfocusedLeadingIconColor = Color(0xFF999999)
                    )
                )
            }

            Spacer(modifier = Modifier.size(10.dp))


               when(uiState){
                   is UiState.Loading ->{
                       CircularProgressIndicator()
                   }
                   is UiState.Error ->{
                       //
                   }

                   is UiState.Success -> {

                       LazyColumn(
                           modifier = Modifier.fillMaxWidth()
                               .padding(5.dp)
                       ) {
                           val sneakers = (uiState as UiState.Success<List<Sneaker>>).data

                           items(sneakers){ sneaker ->
                                   SearchItem(sneaker = sneaker)

                           }
                       }
                   }
               }

        }
    }




}


