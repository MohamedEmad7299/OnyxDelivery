package com.example.onyxdelivery.presentation.session

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor() : ViewModel() {

    private val _sessionExpired = MutableStateFlow(false)

    val sessionExpired: StateFlow<Boolean> = _sessionExpired.asStateFlow()

    fun expireSession() {
        _sessionExpired.value = true
    }

    fun resetSession() {
        _sessionExpired.value = false
    }
}