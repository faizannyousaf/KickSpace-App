package com.example.sneakersapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Update
import com.google.gson.annotations.SerializedName

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "comment") val comment : String,
    @ColumnInfo (name = "sneakerId") val sneakerId: Int,
    @SerializedName("rating") var rating: Float = 0.0f,

    )
