package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.ProfileRepository
import com.leprieto.isntgram.viewmodel.states.ProfileDtoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileRepository: ProfileRepository) :
    ViewModel() {

    var loadedState by mutableStateOf<ProfileDtoState>(ProfileDtoState.Idle)
        private set

    fun getProfile(id: String) {
        viewModelScope.launch {
            loadedState = ProfileDtoState.Loading
            val result = profileRepository.getProfile(id)
            loadedState = if (result.isSuccess) {
                ProfileDtoState.Success(result.getOrNull()!!)
            } else {
                ProfileDtoState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}