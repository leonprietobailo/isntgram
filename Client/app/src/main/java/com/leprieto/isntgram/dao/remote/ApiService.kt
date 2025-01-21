package com.leprieto.isntgram.dao.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body user: RemoteUser): RemoteUserResponse

}