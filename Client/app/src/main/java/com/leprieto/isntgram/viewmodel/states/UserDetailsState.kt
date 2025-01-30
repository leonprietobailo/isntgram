package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.db.UserDetailsLocal

sealed class UserDetailsState {
    data object Idle : UserDetailsState()
    data object Loading : UserDetailsState()
    data class Success(val userDetailsLocal: UserDetailsLocal) : UserDetailsState()
    data class Error(val message: String) : UserDetailsState()
}