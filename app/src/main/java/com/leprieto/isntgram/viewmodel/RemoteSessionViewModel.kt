package com.leprieto.isntgram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.RemoteSession
import com.leprieto.isntgram.model.RemoteSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RemoteSessionViewModel @Inject constructor(private val remoteSessionRepository: RemoteSessionRepository) :
    ViewModel() {

    private val _sessions = MutableStateFlow<List<RemoteSession>>(emptyList())
    val users: StateFlow<List<RemoteSession>?> get() = _sessions

    private fun loadSessions() {
        viewModelScope.launch {
            _sessions.value = remoteSessionRepository.getSessions()
        }
    }

    fun insertSession(session: RemoteSession) {
        viewModelScope.launch {
            remoteSessionRepository.insert(session)
            loadSessions()
        }
    }

    // Needed?
    fun deleteSession(userId: String) {
        viewModelScope.launch {
            remoteSessionRepository.deleteSession(userId)
            loadSessions()
        }
    }

    fun validateSession(userId: String, token: Int): Boolean {
        val session: RemoteSession? = _sessions.value.find { it.userId == userId }
        return session?.let {
            // TODO: Check Token, check Timestamp.
            true
        } ?: false
    }
}