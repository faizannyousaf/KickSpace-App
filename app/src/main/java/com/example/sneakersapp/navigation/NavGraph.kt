package com.example.sneakersapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sneakersapp.presentation.HomeScreen
import com.example.sneakersapp.presentation.LoginScreen
import com.example.sneakersapp.presentation.SearchScreen
import com.example.sneakersapp.presentation.SignUpScreen
import com.example.sneakersapp.presentation.SneakerScreen
import com.example.sneakersapp.viewmodels.LoginViewModel
import com.example.sneakersapp.viewmodels.SignUpViewModel
import com.example.sneakersapp.viewmodels.SneakersViewModel

@Composable
fun NavGraph(navController: NavHostController = rememberNavController(),
             startDestination : String){

    val viewModel: LoginViewModel = viewModel()
    val signUpViewModel: SignUpViewModel = viewModel()
    NavHost(modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(navController, viewModel)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController, signUpViewModel)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Search.route) {
            SearchScreen()
        }
        composable(route = Screen.Sneaker.route,
            arguments = listOf(navArgument("sneakerId"){
                type = NavType.IntType
            })
        ){
            SneakerScreen(it.arguments?.getInt("sneakerId")!!.toInt())
        }
    }
    }



