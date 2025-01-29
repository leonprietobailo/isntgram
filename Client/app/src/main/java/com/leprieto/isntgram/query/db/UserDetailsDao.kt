package com.leprieto.isntgram.query.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leprieto.isntgram.model.db.UserDetailsLocal

@Dao
interface UserDetailsLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDetailsLocal: UserDetailsLocal)

    @Query(value = "DELETE FROM user_details")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM user_details")
    suspend fun getAll(): UserDetailsLocal?
}