package com.example.sneakersapp.model.repositories

import com.example.sneakersapp.model.Dao.SneakerDao
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker

class SneakerRepository(private val sneakerDao: SneakerDao) {

    suspend fun fetchSneakers(): List<Sneaker> {
//        return listOf(
//            Sneaker(
//                1, "Air max 90", "It is from Nike", "", "2020")
//            )
//            Sneaker(
//                2, "New Balance 1906", "It is from New Balace", "", "2022")
//            ),
//            Sneaker(
//                3, "Gel-Kayano", "It is from Asics", "", "2023")
//        )
        return sneakerDao.fetchSneakers()
    }

    suspend fun insertSneaker(sneaker: Sneaker) {
        sneakerDao.insertSneaker(sneaker)
    }

//    suspend fun fetchSneaker() : List<Sneaker>{
//        return sneakerDao.fetchSneakers()
//    }
//    }
}