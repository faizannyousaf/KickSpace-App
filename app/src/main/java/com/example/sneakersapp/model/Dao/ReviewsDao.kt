package com.example.sneakersapp.model.Dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.sneakersapp.model.entities.Review


@Dao
interface ReviewsDao {

    @Insert
    fun insertReview(vararg review: Review)

    @Query("SELECT * FROM review")
    suspend fun fetchReviews() : List<Review>


}