package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.repository.api.ProfileRepository
import com.leprieto.isntgram.viewmodel.states.ProfileDtoState
import com.leprieto.isntgram.viewmodel.states.SearchRequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    var searchProfilesState by mutableStateOf<SearchRequestState>(SearchRequestState.Idle)
        private set
    var loadProfileState by mutableStateOf<ProfileDtoState>(ProfileDtoState.Idle)
        private set

    fun searchProfiles(id: String) {
        viewModelScope.launch {
            val result = profileRepository.getProfiles(id)
            searchProfilesState = if (result.isSuccess) {
                SearchRequestState.Success(result.getOrNull()!!)
            } else {
                SearchRequestState.Error
            }
        }
    }

    fun loadProfile(id: String) {
        viewModelScope.launch {
            val result = profileRepository.getProfile(id)
            loadProfileState = if (result.isSuccess) {
                ProfileDtoState.Success(result.getOrNull()!!)
            } else {
                ProfileDtoState.Error("Error fetching data from server.")
            }
        }
    }
}