package com.leprieto.isntgram.model.api

import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetails(
    @PrimaryKey val id: String,
    val password: String,
    val email: String?
)
