package com.example.sneakersapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakersapp.presentation.HomeScreen
import com.example.sneakersapp.presentation.LoginScreen
import com.example.sneakersapp.viewmodels.LoginViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()){

    val viewModel: LoginViewModel = viewModel()
    NavHost(modifier = Modifier,
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route){
            LoginScreen(navController, viewModel)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
    }

}

