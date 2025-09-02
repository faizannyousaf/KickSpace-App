package com.example.sneakersapp.navigation

sealed class Screen (val route: String){

    object Login : Screen("login")
    object Home : Screen("home")
    object Search: Screen("search")
    object Sneaker: Screen("sneaker/{sneakerId}"){
        fun sneakerDetailRoute(sneakerId: Int) = "sneaker/$sneakerId"
    }


}