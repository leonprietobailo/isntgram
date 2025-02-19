package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.User
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import javax.inject.Inject

class UserDetailsRemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(user: User): GenericApiResponse {
        return try {
            apiService.login(user)
        } catch (e: Exception) {
            e.printStackTrace()
            GenericApiResponse(false, "Unexpected error.")
        }
    }

    suspend fun register(user: User): GenericApiResponse {
        return try {
            apiService.register(user)
        } catch (e: Exception) {
            e.printStackTrace()
            GenericApiResponse(false, "Unexpected error.")
        }
    }
}