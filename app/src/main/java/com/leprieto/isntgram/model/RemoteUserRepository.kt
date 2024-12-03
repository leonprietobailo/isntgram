package com.leprieto.isntgram.model

import com.leprieto.isntgram.dao.RemoteUserDao
import javax.inject.Inject

class RemoteUserRepository @Inject constructor(private val remoteUserDao: RemoteUserDao) {

    suspend fun insert(remoteUser: RemoteUser) {
        remoteUserDao.insert(remoteUser)
    }

    suspend fun getByPk(userId: String): RemoteUser? {
        return remoteUserDao.getByPk(userId)
    }

    suspend fun getAll(): List<RemoteUser> = remoteUserDao.getAll()
}