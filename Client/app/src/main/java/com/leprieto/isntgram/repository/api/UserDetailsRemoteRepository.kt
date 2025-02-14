package com.leprieto.isntgram.repository.api

import com.leprieto.isntgram.model.api.UserDetails
import com.leprieto.isntgram.query.api.ApiService
import com.leprieto.isntgram.query.api.response.GenericApiResponse
import javax.inject.Inject

class UserDetailsRemoteRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun login(userDetails: UserDetails): GenericApiResponse {
        return try {
            apiService.login(userDetails)
        } catch (e: Exception) {
            e.printStackTrace()
            GenericApiResponse(false, "Unexpected error.")
        }
    }

    suspend fun register(userDetails: UserDetails): GenericApiResponse {
        return try {
            apiService.register(userDetails)
        } catch (e: Exception) {
            e.printStackTrace()
            GenericApiResponse(false, "Unexpected error.")
        }
    }
}