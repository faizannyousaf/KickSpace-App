package com.example.sneakersapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.repositories.SneakerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakersViewModel @Inject constructor(
                        private val repository : SneakerRepository) : ViewModel() {
    val scope = CoroutineScope(Job() + Dispatchers.IO)

        suspend fun fetchSneakers() : List<Sneaker>{
            return repository.fetchSneakers()
        }
    suspend fun insertSneaker(){
        scope.launch {
            repository.insertSneaker(Sneaker(1,"Air max","Good shoes","XYZ","2012"))
        }
    }

    }
