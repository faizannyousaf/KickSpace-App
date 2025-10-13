package com.example.sneakersapp.viewmodels


import androidx.lifecycle.ViewModel
import com.example.sneakersapp.model.repositories.ReviewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ReviewsViewModel @Inject constructor(
                       private val repository : ReviewsRepository) : ViewModel() {

//   suspend fun insertDummyData(){
//        repository.insertReviews(Review(1,"very good shoes",
//            2,3,3))
//    }

//    suspend fun fetchReviews() : List<Review>{
//       return repository.fetchReviews()
//    }
}