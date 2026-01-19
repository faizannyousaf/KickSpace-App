package com.example.sneakersapp.model.repositories

import com.example.sneakersapp.model.Dao.SneakerDao
import com.example.sneakersapp.model.entities.Sneaker
import kotlinx.coroutines.flow.Flow

class SneakerRepository(private val sneakerDao: SneakerDao) {


    fun fetchSneakers(): Flow<List<Sneaker>> = sneakerDao.fetchSneakers()

    suspend fun insertSneaker(sneaker: Sneaker) {
        sneakerDao.insertSneaker(sneaker)
    }


    suspend fun insertAllSneakers(sneakers: List<Sneaker>){
        sneakerDao.insertAllSneakers(sneakers)
    }

    suspend fun deleteSneaker(sneaker: Sneaker){
        sneakerDao.deleteSneaker(sneaker)
    }


}