package com.leprieto.isntgram.model.api

data class PostDto(
    val user: UserDetails,
    val caption: String,
//    val postedDate: Date?,
    val url: String?
)