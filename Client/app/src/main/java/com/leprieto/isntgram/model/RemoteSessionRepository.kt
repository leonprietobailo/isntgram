package com.leprieto.isntgram.model

import com.leprieto.isntgram.dao.RemoteSessionDao
import javax.inject.Inject

class RemoteSessionRepository @Inject constructor(private val remoteSessionDao: RemoteSessionDao) {

    suspend fun insert(session: RemoteSession) {
        remoteSessionDao.insert(session)
    }

    suspend fun getSessions(): List<RemoteSession> {
        return remoteSessionDao.getSessions()
    }

    suspend fun deleteSession(userId: String) {
        remoteSessionDao.deleteSession(userId)
    }
}