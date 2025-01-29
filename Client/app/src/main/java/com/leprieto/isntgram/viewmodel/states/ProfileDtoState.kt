package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.api.ProfileDto

sealed class ProfileDtoState {
    data object Idle : ProfileDtoState()
    data object Loading : ProfileDtoState()
    data class Success(val response: ProfileDto) : ProfileDtoState()
    data class Error(val message: String) : ProfileDtoState()
}
