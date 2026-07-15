package com.example.sneakersapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.sneakersapp.presentation.ReviewScreen
import com.example.sneakersapp.presentation.SearchScreen
import com.example.sneakersapp.presentation.SignUpScreen
import com.example.sneakersapp.presentation.SneakerScreen
import com.example.sneakersapp.ui.theme.AddReviewScreen
import com.example.sneakersapp.viewmodels.LoginViewModel
import com.example.sneakersapp.viewmodels.SignUpViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

        composable(Screen.Search.route) {
            SearchScreen(navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController, signUpViewModel)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(route = Screen.AddReview.route,
            arguments = listOf(navArgument("sneakerId"){
                type = NavType.IntType
            })
        ){
            AddReviewScreen(it.arguments?.getInt("sneakerId")!!,navController)
        }

        composable(route = Screen.Sneaker.route,
            arguments = listOf(navArgument("sneakerId"){
                type = NavType.IntType
            })
        ){
            SneakerScreen(it.arguments?.getInt("sneakerId")!!, navController)
        }
        composable(route = Screen.Reviews.route,
            arguments = listOf(navArgument("sneakerId"){
                type = NavType.IntType
            })
        ){
            ReviewScreen(it.arguments?.getInt("sneakerId")!!.toInt(), navController)
        }
    }
    }



