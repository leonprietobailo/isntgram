package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.query.api.response.GenericApiResponse

sealed class GenericRequestState {
    data object Idle : GenericRequestState()
    data object Loading : GenericRequestState()
    data class Success(val response: GenericApiResponse?) : GenericRequestState()
    data class Error(val message: String) : GenericRequestState()
}
