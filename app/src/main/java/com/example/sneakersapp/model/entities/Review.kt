package com.example.sneakersapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Update

@Entity
data class Review(
    @PrimaryKey(autoGenerate = true) var id: Int,
   @ColumnInfo(name = "comment") val comment : String,
   @ColumnInfo(name = "rating") val rating : Int,
    @ColumnInfo (name = "sneakerId") val sneakerId: Int,

)
