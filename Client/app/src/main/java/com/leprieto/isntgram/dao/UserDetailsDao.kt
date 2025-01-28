package com.leprieto.isntgram.dao

//@Dao
//interface UserDetailsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(userDetails: UserDetails)
//
//    @Query(value = "SELECT * FROM user_details WHERE id = :userId")
//    suspend fun getByPk(userId: String): UserDetails?
//
//    @Query(value = "SELECT * FROM user_details")
//    suspend fun getAll(): List<UserDetails>
//}