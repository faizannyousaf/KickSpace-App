package com.example.sneakersapp.model

data class Sneaker(
    var name : String,
    var imageUrl: String?,
    var releaseYear : String,
    var reviews: List<String>
)
