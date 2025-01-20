package com.leprieto.isntgram.model

import com.leprieto.isntgram.dao.LocalSessionDao
import javax.inject.Inject

class LocalSessionRepository @Inject constructor(private val localSessionDao: LocalSessionDao) {

    suspend fun insert(session: LocalSession) {
        localSessionDao.insert(session)
    }

    suspend fun getSessions(): List<LocalSession>? {
        return localSessionDao.getSessions()
    }

    suspend fun deleteSession(userId: String) {
        localSessionDao.deleteSession(userId)
    }
}