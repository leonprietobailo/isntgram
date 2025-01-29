package com.leprieto.isntgram.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "user_details")
@JsonClass(generateAdapter = true)
data class UserDetailsLocal(
    @PrimaryKey val id: String,
    val token: String
)
