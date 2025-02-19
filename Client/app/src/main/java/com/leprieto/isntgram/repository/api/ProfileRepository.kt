package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.Profile
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getProfile(id: String): Result<Profile> {
        return try {
            val response: Profile = apiService.getProfile(id)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun updateProfile(profile: Profile): Result<GenericApiResponse> {
        return try {
            val response: GenericApiResponse = apiService.updateProfile(profile)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun getProfiles(id: String): Result<List<Profile>> {
        return try {
            val response: List<Profile> = apiService.getProfilesByQuery(id)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}