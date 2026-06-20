package com.example.sneakersapp.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.UiState
import com.example.sneakersapp.UserPreferences
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.repositories.ReviewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject


@HiltViewModel
class ReviewsViewModel @Inject constructor(
                       private val repository : ReviewsRepository,
    private val userPreferences: UserPreferences) : ViewModel() {


    var review by mutableStateOf("")
    private var userEmail : String = ""


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

    fun deleteShoe() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute { repository.deleteRows() }
    }
    fun insertReviews(review: Review) {
        viewModelScope.launch {
            repository.insertReviews(review)
        }
    }

    fun getUserEmail() : String{
        userEmail = userPreferences.getUserEmail().toString()
        return userEmail
    }


}