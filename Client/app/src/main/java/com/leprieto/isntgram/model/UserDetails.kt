package com.leprieto.isntgram.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "user_details")
@JsonClass(generateAdapter = true)
data class UserDetails(
    @PrimaryKey @Json(name = "id") val id: String,
    @Json(name = "password") val password: String,
    @Json(name = "email") val email: String,
)
