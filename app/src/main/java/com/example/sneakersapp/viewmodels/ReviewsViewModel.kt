package com.example.sneakersapp.viewmodels


import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakersapp.UiState
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.repositories.ReviewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewsViewModel @Inject constructor(
                       private val repository : ReviewsRepository) : ViewModel() {
     private val _review =  MutableLiveData<String>()
    var review : LiveData<String> = _review


    private val _reviewState = MutableStateFlow<UiState<List<Review>>>(UiState.Loading)
    val reviewState = _reviewState.asStateFlow()



    fun onReviewChange(newReview :String){
        _review.value = newReview
    }


    fun insertReviews(review: Review) {
        viewModelScope.launch {
            repository.insertReviews(review)
        }
    }
//
//
//    fun getReviews(){
//        viewModelScope.launch {
//            repository.fetchReviews()
//                .catch { e->
//                    _reviewState.value = UiState.Error(e.message ?: "Unknown error")
//
//                }
//                .collect{sneakers->
//                    _reviewState.value = UiState.Success(sneakers)
//                }
//
//        }
//    }
}