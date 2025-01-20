package com.leprieto.isntgram.dao

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leprieto.isntgram.model.RemoteUser

@Dao
interface RemoteUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteUser: RemoteUser)

    @Query(value = "SELECT * FROM remote_users WHERE id = :userId")
    suspend fun getByPk(userId: String): RemoteUser?

    @Query(value = "SELECT * FROM remote_users")
    suspend fun getAll(): List<RemoteUser>
}