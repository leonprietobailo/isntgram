package com.leprieto.isntgram.query.api

import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.model.common.UserDetails
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body user: UserDetails): GenericApiResponse

    @POST("auth/register")
    suspend fun register(@Body userDetails: UserDetails): GenericApiResponse

    @GET("app/profiles/{userId}")
    suspend fun getProfile(@Path(value = "userId") id: String): ProfileDto
}