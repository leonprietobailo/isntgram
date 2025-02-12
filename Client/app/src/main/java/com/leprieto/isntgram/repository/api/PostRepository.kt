package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.query.api.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun uploadPost(userId: String, file: File) {
        val part = MultipartBody.Part.createFormData(
            "pic",
            "myPic",
            RequestBody.cre
        )
    }
}