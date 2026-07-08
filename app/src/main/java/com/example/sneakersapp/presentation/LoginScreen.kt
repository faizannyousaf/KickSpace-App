package com.example.sneakersapp.presentation



import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sneakersapp.R
import com.example.sneakersapp.ui.theme.getTextFieldColors
import com.example.sneakersapp.viewmodels.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel : LoginViewModel){


    Scaffold  (
//        topBar = {
//        TopAppBar(
//            colors = topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.primary,
//                titleContentColor = MaterialTheme.colorScheme.onPrimary,
//            ),
//            title = {
//               Column (modifier = Modifier.fillMaxSize(),
//                   verticalArrangement = Arrangement.Center,
//                   horizontalAlignment = Alignment.CenterHorizontally){ Text("Login") }
//            }
//        )

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.kickspace_logo),
                contentDescription = "KickSpace Logo",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                "KickSpace",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.size(8.dp))

            Text(
                "Your Sneaker Community",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.size(20.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                placeholder = { Text("email") },
                singleLine = true,
                colors = getTextFieldColors()
            )

            Spacer(modifier = Modifier.size(8.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("Password") },
                singleLine = true,
                colors = getTextFieldColors()

            )
            Spacer(modifier = Modifier.size(20.dp))

            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimary, disabledContainerColor
                    = MaterialTheme.colorScheme.surface,
                    disabledContentColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .height(60.dp),
                onClick = { viewModel.validateLogin() },
                enabled = !viewModel.isLoading
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Login")
                }
            }

            if (viewModel.loginSuccess) {
                navController.navigate("home")
            }


            Spacer(modifier = Modifier.size(10.dp))

            Row {
                Text(
                    "New user?",
                    modifier = Modifier.clickable { navController.navigate("signup") },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Spacer(modifier =  Modifier.padding(start = 3.dp))
                Text(
                    "Sign Up!",
                    modifier = Modifier.clickable { navController.navigate("signup") },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
            Text(viewModel.errorMessage, color = MaterialTheme.colorScheme.error)

        }
    }

    }

