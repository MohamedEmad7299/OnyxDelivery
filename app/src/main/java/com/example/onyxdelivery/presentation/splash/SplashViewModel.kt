package com.example.onyxdelivery.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onyxdelivery.data.local.session.PreferencesSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesSessionManager: PreferencesSessionManager
) : ViewModel() {

    private val _navigation = MutableStateFlow<SplashNavigation>(SplashNavigation.Idle)
    val navigation: StateFlow<SplashNavigation> = _navigation

    init {

        viewModelScope.launch {

            delay(1500)

            preferencesSessionManager.isLoggedIn.firstOrNull()?.let { loggedIn ->
                _navigation.value = if (loggedIn) {
                    SplashNavigation.ToHome
                } else {
                    SplashNavigation.ToLogin
                }
            } ?: run {
                _navigation.value = SplashNavigation.ToLogin
            }
        }
    }
}
