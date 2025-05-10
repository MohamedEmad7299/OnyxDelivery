package com.example.onyxdelivery.presentation.language

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class LanguageViewModel @Inject constructor(
) : ViewModel() {

    private val _screenState = MutableStateFlow(
        LanguageState(
            selectedLanguage = Language.ENGLISH,
            isArabicSelected = false,
            isEnglishSelected = true
        )
    )

    val screenState = _screenState.asStateFlow()

    fun onChangeLanguage(language: Language) {

        _screenState.value = when (language) {

            Language.ARABIC -> {
                _screenState.value.copy(
                    selectedLanguage = Language.ARABIC,
                    isArabicSelected = true,
                    isEnglishSelected = false
                )
            }

            Language.ENGLISH -> {
                _screenState.value.copy(
                    selectedLanguage = Language.ENGLISH,
                    isArabicSelected = false,
                    isEnglishSelected = true
                )
            }
        }
    }
}