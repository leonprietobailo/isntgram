package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.UserDto
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import javax.inject.Inject

class UserDetailsRemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(userDto: UserDto): GenericApiResponse {
        return try {
            apiService.login(userDto)
        } catch (e: Exception) {
            e.printStackTrace()
            GenericApiResponse(false, "Unexpected error.")
        }
    }

    suspend fun register(userDto: UserDto): GenericApiResponse {
        return try {
            apiService.register(userDto)
        } catch (e: Exception) {
            e.printStackTrace()
            GenericApiResponse(false, "Unexpected error.")
        }
    }
}