package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.api.PostDto
import com.leprieto.isntgram.repository.api.PostRepository
import com.leprieto.isntgram.viewmodel.states.GenericRequestState
import com.leprieto.isntgram.viewmodel.states.PostsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    var imagePostedState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set
    var loadedPostsState by mutableStateOf<PostsState>(PostsState.Idle)
        private set

    fun uploadImage(postDto: PostDto, file: File) {
        viewModelScope.launch {
            imagePostedState = GenericRequestState.Loading
            postRepository.uploadPost(postDto, file)
            imagePostedState = GenericRequestState.Success(null)
        }
    }

    fun getPosts(userId: String) {
        viewModelScope.launch {
            loadedPostsState = PostsState.Loading
            val response = postRepository.getPosts(userId)
            loadedPostsState = PostsState.Success(response)
        }
    }
}