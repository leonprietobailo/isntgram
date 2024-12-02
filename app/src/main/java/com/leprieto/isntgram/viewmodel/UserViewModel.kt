package com.leprieto.isntgram.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leprieto.isntgram.model.User
import com.leprieto.isntgram.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private fun loadUsers() {
        _users.value = userRepository.getUsers()
    }

    fun addUser(user: User) {
        userRepository.addUser(user)
        loadUsers()
    }

    fun getFilteredUsers(startsWith: String): List<User> {
        // TODO: Remove this call.
        loadUsers()
        return _users.value!!.filter { it.id.startsWith(startsWith) }
    }

    fun getProfile(id: String): User {
        // TODO: Remove this call.
        loadUsers()
        return users.value!!.find { it.id == id }!!
    }
}