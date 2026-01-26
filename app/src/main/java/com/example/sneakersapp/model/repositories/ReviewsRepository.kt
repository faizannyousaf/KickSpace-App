package com.example.sneakersapp.model.repositories


import com.example.sneakersapp.model.Dao.ReviewsDao
import com.example.sneakersapp.model.entities.Review
import kotlinx.coroutines.flow.Flow

class ReviewsRepository(private val reviewsDao: ReviewsDao) {

    suspend fun insertReviews(review: Review){
        reviewsDao.insertReview(review)
    }

     fun fetchReviews() : Flow<List<Review>> {
        return reviewsDao.fetchReviews()
    }

    fun fetchReviewsBySneakerID(sneakerId : Int) : Flow<List<Review>> {
        return reviewsDao.fetchReviewsBySneakerID(sneakerId )
    }


}