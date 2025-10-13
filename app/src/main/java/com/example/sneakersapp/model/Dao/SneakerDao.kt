package com.example.sneakersapp.model.Dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.navigation.Screen


@Dao
interface SneakerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSneaker(vararg sneaker: Sneaker)

    @Query("SELECT * FROM review")
     suspend fun fetchSneakers() : List<Sneaker>
}