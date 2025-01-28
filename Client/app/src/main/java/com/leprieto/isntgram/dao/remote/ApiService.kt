package com.leprieto.isntgram.dao.remote

import com.leprieto.isntgram.dao.remote.response.GenericApiResponse
import com.leprieto.isntgram.model.db.UserDetails
import com.leprieto.isntgram.model.dto.ProfileDto
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