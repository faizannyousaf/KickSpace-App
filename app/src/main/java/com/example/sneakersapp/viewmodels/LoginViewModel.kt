package com.example.sneakersapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.UserPreferences
import com.example.sneakersapp.model.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject
constructor(private val userRepository: UserRepository,
  private val userPreferences: UserPreferences) : ViewModel(){


  var email by mutableStateOf("")
  var password by mutableStateOf("")
  var isLoading by mutableStateOf(false)
  var errorMessage by mutableStateOf<String>("")
  var loginSuccess by mutableStateOf(false)


  fun validateLogin() {

    if (!email.contains("@")) {
      errorMessage = "Invalid email format"
      return
    }
    viewModelScope.launch {
      isLoading = true
      val user = userRepository.validateLogin(email, password)

      if(user != null){
        userPreferences.saveUserSession(user.id,user.email)
        loginSuccess = true
      }
      else{
        loginSuccess = false
        errorMessage = "Invalid login"
        isLoading = false
      }

    }
  }

//  fun validateLogin(){
//    viewModelScope.launch {
//      userRepository.validateLogin(email, password)
//    }
//
//
//  }
}