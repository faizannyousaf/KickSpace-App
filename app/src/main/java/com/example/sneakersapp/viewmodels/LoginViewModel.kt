package com.example.sneakersapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
  private  val _email = MutableLiveData<String>()
    var email : LiveData<String> = _email

    fun onEmailChange(newEmail :String){
      _email.value = newEmail
    }


  private val _password = MutableLiveData<String>()
  var password : LiveData<String> = _password

  fun onPasswordChange(newPassword: String){
    _password.value = newPassword
  }


}