package com.leprieto.isntgram.model.common

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "user_details")
@JsonClass(generateAdapter = true)
data class UserDetails(
    @PrimaryKey val id: String,
    @Ignore val password: String,
    @Ignore val email: String?,
)
