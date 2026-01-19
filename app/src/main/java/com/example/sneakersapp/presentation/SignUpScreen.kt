package com.example.sneakersapp.presentation




import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sneakersapp.model.entities.User
import com.example.sneakersapp.viewmodels.SignUpViewModel
import com.example.sneakersapp.viewmodels.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel : SignUpViewModel){
    var result = ""

   // val userViewModel = hiltViewModel<UserViewModel>()

    Scaffold  (topBar = {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){ Text("Sign Up") }
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }
    ){innerPadding ->
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = {viewModel.name = it},
                label = { Text("name") }
            )
            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = {viewModel.email = it},
                label = { Text("email") }
            )
            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = {viewModel.password = it},
                label = { Text("password") }
            )
            Spacer(modifier = Modifier.size(20.dp))

            Button(onClick = {
              viewModel.insertUser()
            }) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(viewModel.errorMessage!! ,color = Color.Black)

        }




    }

}

fun insertUser( name: String, email: String, password : String, viewModel : UserViewModel){
    viewModel.insertUser(User(1,"Faizan", email,password,"",0))

}