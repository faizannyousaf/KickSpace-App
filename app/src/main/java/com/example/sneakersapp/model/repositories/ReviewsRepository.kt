package com.example.sneakersapp.model.repositories


import com.example.sneakersapp.model.Dao.ReviewsDao
import com.example.sneakersapp.model.entities.Review
import kotlinx.coroutines.flow.Flow

class ReviewsRepository(private val reviewsDao: ReviewsDao) {

    suspend fun insertReviews(review: Review){
        reviewsDao.insertReview(review)
    }
//
//    suspend fun fetchReviews() : Flow<List<Review>> {
//        return reviewsDao.fetchReviews()
//    }

}