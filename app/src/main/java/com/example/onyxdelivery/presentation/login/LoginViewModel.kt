package com.example.onyxdelivery.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.local.session.SessionManager
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import com.example.onyxdelivery.data.model.dto.Value
import com.example.onyxdelivery.domain.usecase.CheckDeliveryLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: CheckDeliveryLoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<Resource<LoginResponseDto>>(Resource.InitialState)
    val loginState: StateFlow<Resource<LoginResponseDto>> = _loginState

    fun login(deliveryNo: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading
            val result = loginUseCase(LoginRequestDto(
                value = Value(
                    P_DLVRY_NO = deliveryNo,
                    P_PSSWRD = password,
                    P_LANG_NO = "1",
                    P_PRCSSD_FLG = "0"
                )
            ))
            _loginState.value = result
            if (result is Resource.Success) {
                sessionManager.setLoggedIn(true)
            }
        }
    }
}