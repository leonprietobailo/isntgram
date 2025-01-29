package com.leprieto.isntgram.repository.db

import com.leprieto.isntgram.model.db.UserDetailsLocal
import com.leprieto.isntgram.source.db.UserDetailsLocalSource
import javax.inject.Inject

class UserDetailsLocalRepository @Inject constructor(private val userDetailsLocalSource: UserDetailsLocalSource) {

    suspend fun insertUser(userDetailsLocal: UserDetailsLocal) {
        userDetailsLocalSource.insertUser(userDetailsLocal)
    }

    suspend fun deleteAll() {
        userDetailsLocalSource.deleteAll()
    }

    suspend fun getUser(): UserDetailsLocal? {
        return userDetailsLocalSource.getUser()
    }

}