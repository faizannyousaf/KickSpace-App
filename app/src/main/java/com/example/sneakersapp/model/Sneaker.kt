package com.example.sneakersapp.model

data class Sneaker(
    var id : Int,
    var name : String,
    var description: String?,
    var imageUrl: String?,
    var releaseYear : String,
    var reviews: List<String>
)
