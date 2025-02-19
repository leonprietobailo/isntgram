package com.leprieto.isntgram.model.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    val user: User,
//    val caption: String,
//    val postedDate: Date?,
    val url: String?
)