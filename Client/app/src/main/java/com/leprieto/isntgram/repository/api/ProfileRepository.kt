package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.query.api.ApiService
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getProfile(id: String): Result<ProfileDto> {
        return try {
            val response: ProfileDto = apiService.getProfile(id)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}