package com.example.sneakersapp.model.Dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakersapp.model.entities.Review
import kotlinx.coroutines.flow.Flow


@Dao
interface ReviewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(vararg review: Review)
//
//    @Query("SELECT * FROM review")
//    suspend fun fetchReviews() : Flow<List<Review>>


}