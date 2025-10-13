package com.example.sneakersapp.model.repositories


import com.example.sneakersapp.model.Dao.ReviewsDao
import com.example.sneakersapp.model.entities.Review

class ReviewsRepository(private val reviewsDao: ReviewsDao) {

    suspend fun insertReviews(review: Review){
        reviewsDao.insertReview(review)
    }

    suspend fun fetchReviews() : List<Review>{
        return reviewsDao.fetchReviews()
    }

}