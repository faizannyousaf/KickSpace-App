package com.example.sneakersapp.model.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakersapp.model.entities.Sneaker
import kotlinx.coroutines.flow.Flow


@Dao
interface SneakerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSneaker(sneaker: Sneaker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSneakers(sneakers : List<Sneaker>)

    @Query("SELECT * FROM sneaker")
    fun fetchSneakers() : Flow<List<Sneaker>>

    @Delete
    suspend fun deleteSneaker(sneaker: Sneaker)
}