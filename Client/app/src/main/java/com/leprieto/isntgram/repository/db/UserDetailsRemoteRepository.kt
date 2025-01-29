package com.leprieto.isntgram.repository.db

import com.leprieto.isntgram.model.common.UserDetails
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import javax.inject.Inject

class UserDetailsRemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(userDetails: UserDetails): Result<GenericApiResponse> {
        return try {
            val response: GenericApiResponse = apiService.login(userDetails)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun register(userDetails: UserDetails): Result<GenericApiResponse> {
        return try {
            val response: GenericApiResponse = apiService.register(userDetails)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}