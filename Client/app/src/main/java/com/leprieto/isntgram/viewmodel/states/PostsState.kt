package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.api.Post

sealed class PostsState {
    data object Idle : PostsState()
    data object Loading : PostsState()
    data class Success(val response: List<Post>) : PostsState()
    data class Error(val message: String) : PostsState()
}
