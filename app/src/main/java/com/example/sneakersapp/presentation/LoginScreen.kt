package com.example.sneakersapp.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.sneakersapp.viewmodels.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel : LoginViewModel){


    Scaffold  (topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
               Column (modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally){ Text("Login") }
            }
        )
    }
    ){innerPadding ->
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            InputFields(viewModel)
            Button(onClick = { navController.navigate("home") }) {
                Text("Login")
            }
            }
        }

    }


@Composable
fun InputFields(viewModel: LoginViewModel){
    val email by viewModel.email.observeAsState("")
   OutlinedTextField(
       value = email,
       onValueChange = {
           viewModel.onEmailChange(it)
       },
       label =
           {
               Text("Email")
           }
   )
    val password by viewModel.email.observeAsState("")
    OutlinedTextField(
        value = password,
        onValueChange = {
            viewModel.onPasswordChange(it)
        },
        label = {
           Text ("Password")
        }
    )
}

@Composable
@Preview
fun LoginPreview(){

}