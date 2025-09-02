package com.example.sneakersapp.viewmodels


import androidx.lifecycle.ViewModel
import com.example.sneakersapp.model.Sneaker
import com.example.sneakersapp.model.SneakerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _sneakers = MutableStateFlow<List<Sneaker>>(emptyList())
    val sneakers: StateFlow<List<Sneaker>> = _sneakers

    init {
        fetchSneakers()
    }

    private fun fetchSneakers() {
        _sneakers.value = SneakerRepository.fetchSneakers()
    }



}