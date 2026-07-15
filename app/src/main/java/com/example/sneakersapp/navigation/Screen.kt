package com.example.sneakersapp.navigation


sealed class Screen (val route: String){

    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object Search: Screen("search")
    object AddReview: Screen("addReview/{sneakerId}"){
        fun addReviewRoute(id: Int) = "addReview/$id"
    }
    object Sneaker: Screen(route = "sneaker/{sneakerId}"){
        fun sneakerDetailRoute(id: Int) = "sneaker/$id"
    }

    object Reviews: Screen(route = "reviews/{sneakerId}"){
        fun reviewsRoute(id : Int) = "reviews/$id"
    }


}