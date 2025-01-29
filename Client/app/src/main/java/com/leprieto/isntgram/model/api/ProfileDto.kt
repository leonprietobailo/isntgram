package com.leprieto.isntgram.model.api

data class ProfileDto(
    val name: String?,
    val description: String?,
    val posts: Int,
    val followers: Int,
    val following: Int
)