package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.UserDetails
import com.leprieto.isntgram.model.UserDetailsRemoteRepository
import com.leprieto.isntgram.viewmodel.sealed.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsRemoteViewModel @Inject constructor(
    private val userDetailsRemoteRepository: UserDetailsRemoteRepository
) : ViewModel() {

    var loginState by mutableStateOf<LoginState>(LoginState.Idle)
        private set

    fun login(userDetails: UserDetails) {
        viewModelScope.launch {
            loginState = LoginState.Loading
            val result = userDetailsRemoteRepository.login(userDetails)
            loginState = if (result.isSuccess) {
                LoginState.Success(result.getOrNull())
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun register(userDetails: UserDetails) {
        viewModelScope.launch {
            loginState = LoginState.Loading
            val result = userDetailsRemoteRepository.register(userDetails)
        }
    }
}
