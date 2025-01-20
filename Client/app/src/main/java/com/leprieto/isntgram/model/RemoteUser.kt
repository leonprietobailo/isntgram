package com.leprieto.isntgram.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_users")
data class RemoteUser(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val posts: Int,
    val followers: Int,
    val following: Int
)
