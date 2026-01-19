package com.example.sneakersapp

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()       // only one instance exists
    data class Success<T>(val data: T) : UiState<T>()  // holds data
    data class Error(val message: String) : UiState<Nothing>() // holds an error message
}