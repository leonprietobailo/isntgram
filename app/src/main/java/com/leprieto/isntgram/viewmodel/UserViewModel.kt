package com.leprieto.isntgram.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leprieto.isntgram.model.User
import com.leprieto.isntgram.model.UserRepository

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    private fun loadUsers() {
        _users.value = repository.getUsers()
    }

    fun addUser(user: User) {
        repository.addUser(user)
        loadUsers()
    }

    fun getFilteredUsers(startsWith: String): List<User> {
        // TODO: Remove this call.
        loadUsers()
        return _users.value!!.filter { it.id.startsWith(startsWith) }
    }
}