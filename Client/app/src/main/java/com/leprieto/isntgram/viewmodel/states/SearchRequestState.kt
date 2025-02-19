package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.api.Profile

sealed class SearchRequestState {
    data object Idle : SearchRequestState()
    data object Loading : SearchRequestState()
    data class Success(val profiles: List<Profile>) : SearchRequestState()
    data object Error : SearchRequestState()
}