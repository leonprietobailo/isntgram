package com.leprieto.isntgram.source.db

import com.leprieto.isntgram.model.db.UserDetailsLocal
import com.leprieto.isntgram.query.db.UserDetailsLocalDao
import javax.inject.Inject

class UserDetailsLocalSource @Inject constructor(private val userDetailsDao: UserDetailsLocalDao) {

    suspend fun insertUser(userDetailsLocal: UserDetailsLocal) {
        userDetailsDao.insert(userDetailsLocal)
    }

    suspend fun deleteAll() {
        userDetailsDao.deleteAll()
    }

    suspend fun getUser(): UserDetailsLocal? {
        return userDetailsDao.getAll()
    }

}