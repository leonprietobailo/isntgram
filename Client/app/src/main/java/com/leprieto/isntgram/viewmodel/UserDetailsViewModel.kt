package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.api.UserDto
import com.leprieto.isntgram.model.db.UserDetailsLocal
import com.leprieto.isntgram.repository.api.UserDetailsRemoteRepository
import com.leprieto.isntgram.repository.db.UserDetailsLocalRepository
import com.leprieto.isntgram.viewmodel.states.GenericRequestState
import com.leprieto.isntgram.viewmodel.states.UserDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Split viewmodel into login / register.
@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDetailsRemoteRepository: UserDetailsRemoteRepository,
    private val userDetailsLocalRepository: UserDetailsLocalRepository
) : ViewModel() {

    var loginState by mutableStateOf<UserDetailsState>(UserDetailsState.Idle)
        private set
    var registerState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set

    fun login(userDto: UserDto) {
        viewModelScope.launch {
            loginState = UserDetailsState.Loading
            val result = userDetailsRemoteRepository.login(userDto)
            loginState = if (result.success) {
                // To persist locally.
                val userDetailsLocal = UserDetailsLocal(userDto.id, "")
                userDetailsLocalRepository.deleteAll()
                userDetailsLocalRepository.insertUser(userDetailsLocal)
                UserDetailsState.Success(userDetailsLocal)
            } else {
                UserDetailsState.Error(result.message)
            }
        }
    }

    fun register(userDto: UserDto) {
        viewModelScope.launch {
            registerState = GenericRequestState.Loading
            val result = userDetailsRemoteRepository.register(userDto)
            registerState = if (result.success) {
                GenericRequestState.Success(result)
            } else {
                GenericRequestState.Error(result.message)
            }
        }
    }
}
