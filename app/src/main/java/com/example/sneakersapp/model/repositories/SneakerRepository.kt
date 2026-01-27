package com.example.sneakersapp.model.repositories

import android.content.Context
import com.example.sneakersapp.model.Dao.SneakerDao
import com.example.sneakersapp.model.entities.Sneaker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SneakerRepository @Inject constructor(
    private val sneakerDao: SneakerDao,
    @ApplicationContext private val context: Context // Add this annotation!
) {


    fun fetchSneakers(): Flow<List<Sneaker>> = sneakerDao.fetchSneakers()

//    suspend fun insertSneaker(sneaker: Sneaker) {
//        sneakerDao.insertSneaker(sneaker)
//    }
    suspend fun checkAndSeedDatabase() {

            // 2. Read JSON from assets
            val jsonString = context.assets.open("sneaker_starter_pack.json")
                .bufferedReader().use { it.readText() }

            // 3. Parse JSON to List (using Gson)
            val type = object : TypeToken<List<Sneaker>>() {}.type
            val list: List<Sneaker> = Gson().fromJson(jsonString, type)

            // 4. Insert into Room
            sneakerDao.insertAllSneakers(list)

    }


//    suspend fun insertAllSneakers(sneakers: List<Sneaker>){
//        sneakerDao.insertAllSneakers(sneakers)
//    }

    suspend fun deleteSneaker(sneaker: Sneaker){
        sneakerDao.deleteSneaker(sneaker)
    }


}