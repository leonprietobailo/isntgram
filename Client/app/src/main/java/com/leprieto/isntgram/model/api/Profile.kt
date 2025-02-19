package com.leprieto.isntgram.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Profile(
    val userId: String,
    val name: String?,
    val description: String?,
    val posts: Int,
    val followers: Int,
    val following: Int
)