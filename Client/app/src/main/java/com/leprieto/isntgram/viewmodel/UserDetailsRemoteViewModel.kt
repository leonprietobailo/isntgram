package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.UserDetails
import com.leprieto.isntgram.model.UserDetailsRemoteRepository
import com.leprieto.isntgram.viewmodel.sealed.GenericRequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsRemoteViewModel @Inject constructor(
    private val userDetailsRemoteRepository: UserDetailsRemoteRepository
) : ViewModel() {

    var loginState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set
    var registerState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set

    fun login(userDetails: UserDetails) {
        viewModelScope.launch {
            loginState = GenericRequestState.Loading
            val result = userDetailsRemoteRepository.login(userDetails)
            loginState = if (result.isSuccess) {
                GenericRequestState.Success(result.getOrNull())
            } else {
                GenericRequestState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun register(userDetails: UserDetails) {
        viewModelScope.launch {
            registerState = GenericRequestState.Loading
            val result = userDetailsRemoteRepository.register(userDetails)
            registerState = if (result.isSuccess) {
                GenericRequestState.Success(result.getOrNull())
            } else {
                GenericRequestState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}
