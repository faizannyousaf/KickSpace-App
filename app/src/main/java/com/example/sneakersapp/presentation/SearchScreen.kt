package com.example.sneakersapp.presentation

import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.items.SearchItem
import com.example.sneakersapp.model.items.SneakerItem
import com.example.sneakersapp.navigation.Screen
import com.example.sneakersapp.ui.theme.Outline
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


            Surface(modifier = Modifier.height(150.dp)
                .fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer) {

                OutlinedTextField(modifier = Modifier.height(10.dp)
                    .padding(top = 20.dp, start = 5.dp, end = 5.dp, bottom = 20.dp)
                    .background(color = MaterialTheme.colorScheme.surface),
                    value = query,
                    onValueChange = {query = it},
                    label = { Text("email") },
                    singleLine = true
                )
            }
        }
    }




}


