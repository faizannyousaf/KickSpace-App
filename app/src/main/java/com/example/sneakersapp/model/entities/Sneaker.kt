package com.example.sneakersapp.model.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Sneaker(
    @PrimaryKey
    @SerializedName("sneaker_id")var id: Int,
    @SerializedName ("brand") var brandName : String = "",
    @SerializedName ("name") var name : String = "",
    @SerializedName ("sku") var sku : String = "",
    @SerializedName ("category") var category : String = "",
    @SerializedName ("release_date") var releaseDate : String = "",
    @SerializedName ("retail_price") var retailPrice : String = "",
    @SerializedName("description")var description: String = "",
    @SerializedName("image_url") var imageUrl: String = ""
)
