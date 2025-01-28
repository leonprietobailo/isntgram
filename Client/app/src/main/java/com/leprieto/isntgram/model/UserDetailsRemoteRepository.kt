package com.leprieto.isntgram.model

import com.leprieto.isntgram.dao.remote.ApiService
import com.leprieto.isntgram.dao.remote.response.GenericApiResponse
import com.leprieto.isntgram.model.db.UserDetails
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