package com.leprieto.isntgram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.RemoteUser
import com.leprieto.isntgram.model.RemoteUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteUserViewModel @Inject constructor(private val remoteUserRepository: RemoteUserRepository) :
    ViewModel() {

    private val _users = MutableStateFlow<List<RemoteUser>>(emptyList())
    val users: StateFlow<List<RemoteUser>> get() = _users

    private fun loadUsers() {
        viewModelScope.launch {
            _users.value = remoteUserRepository.getAll()
        }
    }

    fun addUser(remoteUser: RemoteUser) {
        viewModelScope.launch {
            remoteUserRepository.insert(remoteUser)
            loadUsers()
        }
    }

    fun getFilteredUsers(startsWith: String): StateFlow<List<RemoteUser>> {
        val filteredUsers = MutableStateFlow<List<RemoteUser>>(_users.value)
        viewModelScope.launch {
            filteredUsers.value = _users.value.filter { it.id.startsWith(startsWith) }
        }
        return filteredUsers
    }

    fun getProfile(id: String): StateFlow<RemoteUser?> {
        val remoteUserFlow = MutableStateFlow<RemoteUser?>(null)
        viewModelScope.launch {
            remoteUserFlow.value = _users.value.find { it.id == id }
        }
        return remoteUserFlow
    }
}