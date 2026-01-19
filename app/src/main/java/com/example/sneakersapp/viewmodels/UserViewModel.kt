package com.example.sneakersapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.model.entities.User
import com.example.sneakersapp.model.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel@Inject constructor(
    private val repository : UserRepository
) : ViewModel(){

    fun insertUser(user : User){
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
}