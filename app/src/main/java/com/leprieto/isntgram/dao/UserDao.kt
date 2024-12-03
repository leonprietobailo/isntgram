package com.leprieto.isntgram.dao

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leprieto.isntgram.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query(value = "SELECT * FROM users WHERE id = :userId")
    suspend fun getByPk(userId: String): User?

    @Query(value = "SELECT * FROM users")
    suspend fun getAll(): List<User>
}