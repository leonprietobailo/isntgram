package com.leprieto.isntgram.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leprieto.isntgram.model.RemoteSession

@Dao
interface RemoteSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: RemoteSession)

    @Query(value = "SELECT * FROM remote_sessions")
    suspend fun getSessions(): List<RemoteSession>

    @Query(value = "DELETE FROM remote_sessions WHERE userId = :user")
    suspend fun deleteSession(user: String)
}