package com.example.sneakersapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sneakersapp.model.Dao.ReviewsDao
import com.example.sneakersapp.model.Dao.SneakerDao
import com.example.sneakersapp.model.Dao.UserDao
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.entities.User

@Database(entities = [User::class, Review::class,Sneaker::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    //abstract fun userDao(): UserDao
    abstract fun reviewDao(): ReviewsDao
    abstract fun sneakerDao(): SneakerDao



}