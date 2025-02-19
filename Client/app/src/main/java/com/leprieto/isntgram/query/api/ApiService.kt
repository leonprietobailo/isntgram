package com.leprieto.isntgram.query.api

import com.leprieto.isntgram.model.api.Post
import com.leprieto.isntgram.model.api.Profile
import com.leprieto.isntgram.model.api.User
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
    suspend fun login(@Body user: User): GenericApiResponse

    // TODO: Replace with put.
    @POST("auth/register")
    suspend fun register(@Body user: User): GenericApiResponse

    @GET("app/profiles/{userId}")
    suspend fun getProfile(@Path(value = "userId") id: String): Profile

    @POST("app/profiles/update")
    suspend fun updateProfile(
        @Body profile: Profile
    ): GenericApiResponse

    @GET("app/profiles/search/{query}")
    suspend fun getProfilesByQuery(@Path(value = "query") id: String): List<Profile>

    @Multipart
    @POST("app/upload/posts/{userId}")
    suspend fun uploadPost(
        @Path("userId") userId: String,
        @Part part: MultipartBody.Part
    ): GenericApiResponse

    @GET("app/profiles/{userId}/posts")
    suspend fun getPosts(@Path("userId") userId: String): List<Post>

}