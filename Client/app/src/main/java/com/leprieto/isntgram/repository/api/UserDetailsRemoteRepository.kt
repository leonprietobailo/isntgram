package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.UserDetailsRemote
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import javax.inject.Inject

class UserDetailsRemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(userDetailsRemote: UserDetailsRemote): Result<GenericApiResponse> {
        return try {
            val response: GenericApiResponse = apiService.login(userDetailsRemote)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    suspend fun register(userDetailsRemote: UserDetailsRemote): Result<GenericApiResponse> {
        return try {
            val response: GenericApiResponse = apiService.register(userDetailsRemote)
            Result.success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}