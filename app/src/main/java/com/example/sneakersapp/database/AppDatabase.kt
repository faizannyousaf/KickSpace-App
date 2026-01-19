package com.example.sneakersapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sneakersapp.model.Dao.ReviewsDao
import com.example.sneakersapp.model.Dao.SneakerDao
import com.example.sneakersapp.model.Dao.UserDao
import com.example.sneakersapp.model.entities.Review
import com.example.sneakersapp.model.entities.Sneaker
import com.example.sneakersapp.model.entities.User
import com.example.sneakersapp.model.repositories.UserRepository

@Database(version = 5, entities = [User::class,
    Review::class,Sneaker::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reviewDao(): ReviewsDao
    abstract fun sneakerDao(): SneakerDao
    abstract fun userDao(): UserDao

}