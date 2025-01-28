package com.leprieto.isntgram.model.db

import com.squareup.moshi.JsonClass

//@Entity(tableName = "user_details")
@JsonClass(generateAdapter = true)
data class UserDetails(
//    @PrimaryKey
    val id: String,
    val password: String,
    val email: String?,
)
