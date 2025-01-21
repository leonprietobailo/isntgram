package com.leprieto.isntgram.dao.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteUser(
    @Json(name = "id") val id: String,
    @Json(name = "password") val password: String,
    @Json(name = "email") val email: String
)
