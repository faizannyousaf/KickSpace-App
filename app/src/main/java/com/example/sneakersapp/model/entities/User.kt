package com.example.sneakersapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["email"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo (name = "name") var name: String,
    @ColumnInfo(name = "email") var email : String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo (name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "reviewCount") var reviewCount : Int

)
