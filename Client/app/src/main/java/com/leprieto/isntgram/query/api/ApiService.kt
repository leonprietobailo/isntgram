package com.leprieto.isntgram.query.api

import com.leprieto.isntgram.model.api.PostDto
import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.model.api.UserDto
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
    suspend fun login(@Body user: UserDto): GenericApiResponse

    // TODO: Replace with put.
    @POST("auth/register")
    suspend fun register(@Body userDto: UserDto): GenericApiResponse

    @GET("app/profiles/{userId}")
    suspend fun getProfile(@Path(value = "userId") id: String): ProfileDto

    @POST("app/profiles/update")
    suspend fun updateProfile(
        @Body profileDto: ProfileDto
    ): GenericApiResponse

    @GET("app/profiles/search/{query}")
    suspend fun getProfilesByQuery(@Path(value = "query") id: String): List<ProfileDto>

    @Multipart
    @POST("app/upload/posts/{userId}")
    suspend fun uploadPost(
        @Path("userId") userId: String,
        @Part part: MultipartBody.Part
    ): GenericApiResponse

    @GET("app/profiles/{userId}/posts")
    suspend fun getPosts(@Path("userId") userId: String): List<PostDto>

}