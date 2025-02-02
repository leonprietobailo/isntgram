package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.ProfileDto
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
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

    suspend fun updateProfile(profileDto: ProfileDto): Result<GenericApiResponse> {
        return try {
            val response: GenericApiResponse = apiService.updateProfile(profileDto)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun getProfiles(id: String): Result<List<ProfileDto>> {
        return try {
            val response: List<ProfileDto> = apiService.getProfilesByQuery(id)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}