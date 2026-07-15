package com.example.sneakersapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AddReviewModel : ViewModel() {

    var comment by mutableStateOf("")

    var selectedRating by mutableStateOf(0)

//    fun onRatingChanged(rating: Int) {
//        selectedRating = rating
//    }

}