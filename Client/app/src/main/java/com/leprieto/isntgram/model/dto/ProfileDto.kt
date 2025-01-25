package com.leprieto.isntgram.model.dto

data class ProfileDto(
    val name: String,
    val description: String,
    val posts: Int,
    val followers: Int,
    val following: Int
)