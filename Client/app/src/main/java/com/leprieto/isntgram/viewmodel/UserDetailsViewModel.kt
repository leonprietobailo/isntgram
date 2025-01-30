package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.api.UserDetailsRemote
import com.leprieto.isntgram.model.db.UserDetailsLocal
import com.leprieto.isntgram.repository.api.UserDetailsRemoteRepository
import com.leprieto.isntgram.repository.db.UserDetailsLocalRepository
import com.leprieto.isntgram.viewmodel.states.GenericRequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Split viewmodel into login / register.
@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDetailsRemoteRepository: UserDetailsRemoteRepository,
    private val userDetailsLocalRepository: UserDetailsLocalRepository
) : ViewModel() {

    var loginState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set
    var registerState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set
//    var accountState by mutableStateOf<UserDetailsState>(UserDetailsState.Idle)
//        private set

    fun login(userDetailsRemote: UserDetailsRemote) {
        viewModelScope.launch {
            loginState = GenericRequestState.Loading
            val result = userDetailsRemoteRepository.login(userDetailsRemote)
            loginState = if (result.isSuccess) {
                // To persist locally.
                val userDetailsLocal = UserDetailsLocal(userDetailsRemote.id, "")
                userDetailsLocalRepository.deleteAll()
                userDetailsLocalRepository.insertUser(userDetailsLocal)
                GenericRequestState.Success(result.getOrNull())
            } else {
                GenericRequestState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun register(userDetailsRemote: UserDetailsRemote) {
        viewModelScope.launch {
            registerState = GenericRequestState.Loading
            val result = userDetailsRemoteRepository.register(userDetailsRemote)
            registerState = if (result.isSuccess) {
                GenericRequestState.Success(result.getOrNull())
            } else {
                GenericRequestState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

//    fun loadLoggedUser() {
//        viewModelScope.launch {
//            accountState = UserDetailsState.Loading
//            val userDetailsLocal = userDetailsLocalRepository.getUser()
//            accountState = if (userDetailsLocal == null) {
//                UserDetailsState.Error("Missing user -> login again.")
//            } else {
//                UserDetailsState.Success(userDetailsLocal)
//            }
//        }
//    }
}
