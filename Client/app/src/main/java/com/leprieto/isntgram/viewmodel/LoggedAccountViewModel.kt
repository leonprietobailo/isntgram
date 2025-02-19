package com.leprieto.isntgram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.api.Profile
import com.leprieto.isntgram.repository.api.ProfileRepository
import com.leprieto.isntgram.repository.db.UserDetailsLocalRepository
import com.leprieto.isntgram.viewmodel.states.GenericRequestState
import com.leprieto.isntgram.viewmodel.states.ProfileDtoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@Deprecated("Replace with UserDetailsViewModel.")
class LoggedAccountViewModel @Inject constructor(
    private val userDetailsLocalRepository: UserDetailsLocalRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    var selfLoadState by mutableStateOf<ProfileDtoState>(ProfileDtoState.Idle)
        private set
    var selfUpdateState by mutableStateOf<GenericRequestState>(GenericRequestState.Idle)
        private set
//    var searchRequestState by mutableStateOf<SearchRequestState>(SearchRequestState.Idle)
//        private set

    fun loadProfile() {
        viewModelScope.launch {
            selfLoadState = ProfileDtoState.Loading
            val user = userDetailsLocalRepository.getUser()
            selfLoadState = if (user == null) {
                ProfileDtoState.Error("User is not logged in.")
            } else {
                val result = profileRepository.getProfile(user.id)
                if (result.isSuccess) {
                    ProfileDtoState.Success(result.getOrNull()!!)
                } else {
                    ProfileDtoState.Error("Error fetching data from server.")
                }
            }
        }
    }

    fun updateProfile(profile: Profile) {
        viewModelScope.launch {
            val result = profileRepository.updateProfile(profile)
            selfUpdateState = if (result.isSuccess) {
                GenericRequestState.Success(result.getOrNull())
            } else {
                GenericRequestState.Error("Error updating user profile.")
            }
        }
    }

//    fun searchProfiles(id: String) {
//        viewModelScope.launch {
//            val result = profileRepository.getProfiles(id)
//            searchRequestState = if (result.isSuccess) {
//                SearchRequestState.Success(result.getOrNull()!!)
//            } else {
//                SearchRequestState.Error
//            }
//        }
//    }
}