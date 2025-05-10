package com.example.onyxdelivery.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.local.session.PreferencesSessionManager
import com.example.onyxdelivery.data.model.dto.LoginRequestDto
import com.example.onyxdelivery.data.model.dto.LoginResponseDto
import com.example.onyxdelivery.data.model.dto.LoginValue
import com.example.onyxdelivery.domain.usecase.CheckDeliveryLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: CheckDeliveryLoginUseCase,
    private val preferencesSessionManager: PreferencesSessionManager
) : ViewModel() {


    private val _screenState = MutableStateFlow(LoginState(
        userId = "",
        password = "",
        errorText = "",
    ))

    val screenState = _screenState.asStateFlow()

    private val _loginResultState = MutableStateFlow<Resource<LoginResponseDto>>(Resource.InitialState)
    val loginResultState: StateFlow<Resource<LoginResponseDto>> = _loginResultState

    fun login(deliveryNo: String, password: String) {
        viewModelScope.launch {
            _loginResultState.value = Resource.Loading
            val result = loginUseCase(LoginRequestDto(
                value = LoginValue(
                    P_DLVRY_NO = deliveryNo,
                    P_PSSWRD = password,
                    P_LANG_NO = "1",
                    P_PRCSSD_FLG = "0"
                )
            ))
            _loginResultState.value = result
            if (result is Resource.Success) {
                preferencesSessionManager.setLoggedIn(true)
            }
        }
    }

    fun onChangePassword(newPassword : String){

        _screenState.update { it.copy(password = newPassword) }
    }
    fun onChangeUserID(newUserID : String){

        _screenState.update { it.copy(userId = newUserID) }
    }
    fun onChangeErrorMessage(newErrorText : String?){

        _screenState.update { it.copy(errorText = newErrorText) }
    }

}