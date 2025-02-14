package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.PostDto
import com.leprieto.isntgram.query.api.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun uploadPost(postDto: PostDto, file: File) {
        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("image", file.name, requestBody)
        apiService.uploadPost(postDto.userId, part)
    }
}