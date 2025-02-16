package com.leprieto.isntgram.viewmodel.states

import com.leprieto.isntgram.model.api.PostDto

sealed class PostsState {
    data object Idle : PostsState()
    data object Loading : PostsState()
    data class Success(val response: List<PostDto>) : PostsState()
    data class Error(val message: String) : PostsState()
}
