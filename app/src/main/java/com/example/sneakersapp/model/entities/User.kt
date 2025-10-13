package com.example.sneakersapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey var id: Int,
    @ColumnInfo (name = "name") var name: String,
    @ColumnInfo (name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "reviewCount") var reviewCount : Int

)
