package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.dao.remote.ApiService
import com.leprieto.isntgram.dao.remote.RemoteUser
import com.leprieto.isntgram.dao.remote.RemoteUserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _loginState = mutableStateOf<RemoteUserResponse?>(null)
    val loginState: State<RemoteUserResponse?> = _loginState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading


    fun login(remoteUser: RemoteUser): RemoteUserResponse {
        viewModelScope.launch {
            _isLoading.value = true
            

        }
    }
}
