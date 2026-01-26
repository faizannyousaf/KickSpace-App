package com.example.sneakersapp.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.UiState
import com.example.sneakersapp.UserPreferences
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.repositories.SneakerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch


@HiltViewModel
class SneakersViewModel @Inject constructor(
    private val sneakerRepository: SneakerRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _sneakerState = MutableStateFlow<UiState<List<Sneaker>>>(UiState.Loading)
    val sneakerState = _sneakerState.asStateFlow()

    fun deleteSneaker(sneaker: Sneaker){
        viewModelScope.launch(Dispatchers.IO) {
            sneakerRepository.deleteSneaker(sneaker)
        }
    }




    init {

        viewModelScope.launch(Dispatchers.IO) {
            sneakerRepository.insertAllSneakers(
                listOf(
                    Sneaker(1, "Nike", "Nice Shoes", "Hello", "1999"),
                    Sneaker(2, "Adidas", "Comfortable Shoes", "Hello", "1999"),
                    Sneaker(3, "Reebok", "Uncomfortable Shoes", "Hello", "1999"),
                    Sneaker(4, "New Balance", "Excellent shoes Shoes", "Hello", "1999"),
                    Sneaker(5, "Armani", "Excellent shoes Shoes", "Hello", "1999"),

                )
            )
        }

        getSneakers()
    }

    private fun getSneakers(){
        viewModelScope.launch {
            sneakerRepository.fetchSneakers()
                .catch { e->
                _sneakerState.value = UiState.Error(e.message ?: "Unknown error")

            }
                .collect{sneakers->
                    _sneakerState.value = UiState.Success(sneakers)
                }

        }
    }

}
