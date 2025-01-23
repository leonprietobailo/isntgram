package com.leprieto.isntgram.dao

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leprieto.isntgram.model.UserDetails

@Dao
interface UserDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDetails: UserDetails)

    @Query(value = "SELECT * FROM user_details WHERE id = :userId")
    suspend fun getByPk(userId: String): UserDetails?

    @Query(value = "SELECT * FROM user_details")
    suspend fun getAll(): List<UserDetails>
}