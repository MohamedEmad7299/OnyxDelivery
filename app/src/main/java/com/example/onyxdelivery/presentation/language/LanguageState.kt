package com.example.onyxdelivery.presentation.language

data class LanguageState (
    val selectedLanguage: Language,
    val isArabicSelected: Boolean,
    val isEnglishSelected: Boolean,
)

enum class Language {
    ARABIC,
    ENGLISH
}