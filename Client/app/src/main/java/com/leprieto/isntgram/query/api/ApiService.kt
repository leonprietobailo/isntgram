package com.leprieto.isntgram.query.api

import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.model.api.UserDetailsRemote
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    @GET("app/profiles/search/{query}")
    suspend fun getProfilesByQuery(@Path(value = "query") id: String): List<ProfileDto>

    @Multipart
    @POST("api/app/upload/posts")
    suspend fun uploadPost(
        @Part("userId") userId: String,
        @Part part: MultipartBody.Part
    ): GenericApiResponse
}