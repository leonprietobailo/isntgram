package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.api.ProfileDto

sealed class SearchRequestState {
    data object Idle : SearchRequestState()
    data object Loading : SearchRequestState()
    data class Success(val profiles: List<ProfileDto>) : SearchRequestState()
    data object Error : SearchRequestState()
}