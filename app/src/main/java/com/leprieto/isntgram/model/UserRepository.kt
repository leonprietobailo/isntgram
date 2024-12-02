package com.leprieto.isntgram.model

import com.leprieto.isntgram.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getByPk(userId: String): User? {
        return userDao.getByPk(userId)
    }
    
    suspend fun getAll(): List<User> = userDao.getAll()
}