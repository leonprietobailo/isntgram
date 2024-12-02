package com.leprieto.isntgram.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val posts: Int,
    val followers: Int,
    val following: Int
)
