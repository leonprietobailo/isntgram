package com.leprieto.isntgram.dao.remote

import com.leprieto.isntgram.dao.remote.response.GenericApiResponse
import com.leprieto.isntgram.model.UserDetails
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login")
    suspend fun login(@Body user: UserDetails): GenericApiResponse

    @POST("register")
    suspend fun register(@Body userDetails: UserDetails): GenericApiResponse
}