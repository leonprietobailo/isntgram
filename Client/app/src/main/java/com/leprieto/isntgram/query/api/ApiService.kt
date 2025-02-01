package com.leprieto.isntgram.query.api

import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.model.api.UserDetailsRemote
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body user: UserDetailsRemote): GenericApiResponse

    // TODO: Replace with put.
    @POST("auth/register")
    suspend fun register(@Body userDetailsRemote: UserDetailsRemote): GenericApiResponse

    @GET("app/profiles/{userId}")
    suspend fun getProfile(@Path(value = "userId") id: String): ProfileDto

    @POST("app/profiles/update")
    suspend fun updateProfile(
        @Body profileDto: ProfileDto
    ): GenericApiResponse
}