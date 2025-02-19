package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.api.Profile

sealed class ProfileDtoState {
    data object Idle : ProfileDtoState()
    data object Loading : ProfileDtoState()
    data class Success(val response: Profile) : ProfileDtoState()
    data class Error(val message: String) : ProfileDtoState()
}
