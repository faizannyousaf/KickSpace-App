package com.example.sneakersapp.model.repositories

import com.example.sneakersapp.model.Dao.UserDao
import com.example.sneakersapp.model.entities.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun validateLogin(email: String, password: String): User?
    {
        return userDao.validateLogin(email, password)
    }

}