package com.example.sneakersapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sneakersapp.model.SneakerRepository
import com.example.sneakersapp.presentation.HomeScreen
import com.example.sneakersapp.presentation.LoginScreen
import com.example.sneakersapp.presentation.SearchScreen
import com.example.sneakersapp.presentation.SneakerScreen
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

        composable(Screen.Search.route) {
            SearchScreen()
        }

        composable(Screen.Sneaker.route,
            listOf(navArgument("sneakerId") { type = NavType.IntType })
        ) {backStackEntry ->
            val sneakerId = backStackEntry.arguments?.getInt("sneakerId")
            val sneaker = SneakerRepository.fetchSneakers().firstOrNull { it.id == sneakerId }
            SneakerScreen(sneaker)

        }
    }

}

