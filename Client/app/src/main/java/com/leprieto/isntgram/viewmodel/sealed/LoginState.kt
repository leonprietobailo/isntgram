package com.leprieto.isntgram.viewmodel.sealed

import com.leprieto.isntgram.dao.remote.response.GenericApiResponse

sealed class LoginState {
    data object Idle : LoginState()
    data object Loading : LoginState()
    data class Success(val user: GenericApiResponse?) : LoginState()
    data class Error(val message: String) : LoginState()
}