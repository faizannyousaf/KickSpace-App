package com.example.sneakersapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sneaker(
    @PrimaryKey var id: Int?,
    @ColumnInfo (name = "name") var name : String = "",
    @ColumnInfo(name = " description")var description: String = "",
    @ColumnInfo(name = "imageUrl") var imageUrl: String = "",
    @ColumnInfo (name = "releaseYear") var releaseYear : String = ""
    //@ColumnInfo(name = "reviews") var reviews: List<Review>
)
