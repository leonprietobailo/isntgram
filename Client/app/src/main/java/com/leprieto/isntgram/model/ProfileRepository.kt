package com.leprieto.isntgram.model

import com.leprieto.isntgram.dao.remote.ApiService
import com.leprieto.isntgram.model.dto.ProfileDto
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