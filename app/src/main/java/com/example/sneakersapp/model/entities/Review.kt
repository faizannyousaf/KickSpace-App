package com.example.sneakersapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["id"]),
    ForeignKey(entity = Sneaker::class, parentColumns = ["id"], childColumns = ["id"])
])
data class Review(
   @PrimaryKey var id: Int,
   @ColumnInfo(name = "comment") val comment : String,
    val rating : Int,

    //Foreign keys
    @ColumnInfo(name = "authorId") val authorId : Int,
    @ColumnInfo (name = "sneakerId") val sneakerId: Int,

)
