package com.leprieto.isntgram.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leprieto.isntgram.model.LocalSession

@Dao
interface LocalSessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: LocalSession)

    @Query(value = "SELECT * FROM local_sessions")
    suspend fun getSessions(): List<LocalSession>?

    @Query(value = "DELETE FROM local_sessions WHERE userId = :user")
    suspend fun deleteSession(user: String)
}