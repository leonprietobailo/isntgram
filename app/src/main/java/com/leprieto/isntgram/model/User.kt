package com.leprieto.isntgram.model

data class User(
    val id: String,
    val name: String,
    val description: String,
    val posts: Int,
    val followers: Int,
    val following: Int
)
