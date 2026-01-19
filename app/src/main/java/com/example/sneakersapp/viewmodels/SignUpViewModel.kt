package com.example.sneakersapp.viewmodels



import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.sneakersapp.model.entities.User
import com.example.sneakersapp.model.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel@Inject
constructor(private val userRepository: UserRepository):ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMessage by mutableStateOf<String>("")


    fun insertUser() {
        if(name.isBlank() || email.isBlank() || password.isBlank()){
             errorMessage = "Please fill all fields"
            return
        }

        if (!email.contains("@")) {
            errorMessage = "Invalid email format"
            return
        }

        if (password.length < 6) {
            errorMessage = "Password must be at least 6 characters"
            return
        }

            viewModelScope.launch(Dispatchers.IO) {

                userRepository.insertUser(
                    User(0, name,email, password ,
                        imageUrl = "", reviewCount = 0)
                )
            }

        }


}