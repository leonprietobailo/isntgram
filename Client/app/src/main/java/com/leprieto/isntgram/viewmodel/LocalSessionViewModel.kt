package com.leprieto.isntgram.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leprieto.isntgram.model.LocalSession
import com.leprieto.isntgram.model.LocalSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalSessionViewModel @Inject constructor(private val localSessionRepository: LocalSessionRepository) :
    ViewModel() {

    private val _sessions = MutableStateFlow<List<LocalSession>?>(emptyList())
    val users: StateFlow<List<LocalSession>?> get() = _sessions

    private fun loadSessions() {
        viewModelScope.launch {
            _sessions.value = localSessionRepository.getSessions()
        }
    }

    fun insertSession(session: LocalSession) {
        viewModelScope.launch {
            localSessionRepository.insert(session)
            loadSessions()
        }
    }

    fun deleteSession(userId: String) {
        viewModelScope.launch {
            localSessionRepository.deleteSession(userId)
            loadSessions()
        }
    }
}