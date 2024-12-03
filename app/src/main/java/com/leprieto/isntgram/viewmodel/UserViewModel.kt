package com.leprieto.isntgram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.User
import com.leprieto.isntgram.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    private fun loadUsers() {
        viewModelScope.launch {
            _users.value = userRepository.getAll()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.insert(user)
            loadUsers()
        }
    }

    fun getFilteredUsers(startsWith: String): StateFlow<List<User>> {
        val filteredUsers = MutableStateFlow<List<User>>(_users.value)
        viewModelScope.launch {
            filteredUsers.value = _users.value.filter { it.id.startsWith(startsWith) }
        }
        return filteredUsers
    }

    fun getProfile(id: String): StateFlow<User?> {
        val userFlow = MutableStateFlow<User?>(null)
        viewModelScope.launch {
            userFlow.value = _users.value.find { it.id == id }
        }
        return userFlow
    }
}