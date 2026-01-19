package com.example.sneakersapp.model.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakersapp.model.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user : User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun validateLogin(email: String, password: String): User?

}