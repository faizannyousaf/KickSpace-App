package com.example.sneakersapp.presentation




import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sneakersapp.R
import com.example.sneakersapp.model.entities.User
import com.example.sneakersapp.ui.theme.getTextFieldColors
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
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Column (modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){ Text("") }
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }
    ){innerPadding ->

        val context = LocalContext.current
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally){

            Image(
                painter = painterResource(id = R.drawable.kickspace_logo),
                contentDescription = "KickSpace Logo",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                "Create Account",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                "Join the Sneaker Community",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.size(15.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                value = viewModel.name,
                onValueChange = {viewModel.name = it},
                placeholder = { Text("name") },
                maxLines = 1,
                colors = getTextFieldColors()
            )
            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                value = viewModel.email,
                onValueChange = {viewModel.email = it},
                placeholder = { Text("email") },
                maxLines = 1,
                colors = getTextFieldColors()
            )
            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                value = viewModel.password,
                onValueChange = {viewModel.password = it},
                placeholder = { Text("password") },
                visualTransformation = PasswordVisualTransformation(),
                maxLines = 1,
                colors = getTextFieldColors()
            )
            Spacer(modifier = Modifier.size(20.dp))

            Button(colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary, disabledContainerColor
                = MaterialTheme.colorScheme.surface,
                disabledContentColor = MaterialTheme.colorScheme.surface
            ),
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(60.dp),
                onClick = {
              viewModel.insertUser()
                Toast.makeText(context,"Account created, please go back to Login page",Toast.LENGTH_LONG).show()
            }) {
                Text("Sign Up")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(viewModel.errorMessage!! ,color = Color.Black)

        }

    }

}