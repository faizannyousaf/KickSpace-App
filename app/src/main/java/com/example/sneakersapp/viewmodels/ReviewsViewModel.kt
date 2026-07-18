package com.example.sneakersapp.viewmodels


import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sneakersapp.UiState
import com.example.sneakersapp.UserPreferences
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.repositories.ReviewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject


@HiltViewModel
class ReviewsViewModel @Inject constructor(
                       private val repository : ReviewsRepository,
    private val userPreferences: UserPreferences) : ViewModel() {

        //Search bar
    var userQuery by mutableStateOf("")

    var review by mutableStateOf("")


    var comment by mutableStateOf("")
    var selectedRating by mutableStateOf(0)

    var reviewCount by mutableIntStateOf(0)
    var ratingAverage by mutableFloatStateOf(0.0F)

    private val _reviewsState = MutableStateFlow<UiState<List<Review>>>(UiState.Loading)
    val reviewsState: StateFlow<UiState<List<Review>>> = _reviewsState.asStateFlow()


    fun loadReviewsForSneaker(sneakerId: Int) {
        viewModelScope.launch {
            deleteShoe()
        }
        viewModelScope.launch {
            repository.fetchReviewsBySneakerID(sneakerId)
                .map<List<Review>, UiState<List<Review>>> { reviews ->
                    UiState.Success(reviews)
                }
                .catch { exception ->
                    emit(UiState.Error(exception.message ?: "Failed to load reviews"))
                }
                .collect { state ->
                    _reviewsState.value = state
                }
        }
    }

    private fun deleteShoe() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute { repository.deleteRows() }
    }
    fun insertReviews(review: Review) {
        viewModelScope.launch {
            repository.insertReviews(review)
        }
    }


    fun getUserName() : String{
       return userPreferences.getUserName().toString()
    }

      fun getReviewsCount(sneakerId : Int)  {
          viewModelScope.launch {
              reviewCount = repository.getReviewsCount(sneakerId)
          }
    }

    fun getAverageRating(sneakerId : Int){
        viewModelScope.launch {
           ratingAverage = repository.getRatingAverage(sneakerId)?: 0f
        }
    }

    fun filteredShoes(shoeList: List<Sneaker>) : List<Sneaker> {
       return shoeList.filter { sneaker ->
            sneaker.name.contains(userQuery, ignoreCase = true) ||
           sneaker.brandName.contains(userQuery, ignoreCase = true)
        }

    }



}