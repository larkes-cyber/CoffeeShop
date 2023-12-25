package com.example.coffeeshop.android.screen.profile_screen

data class ProfileUIState(
    val isNameFormActive:Boolean = false,
    val isNumberFormActive:Boolean = false,
    val nameTextField:String = "",
    val numberTextField:String = "",
    val selectedLang:String = "",
    val isPickerActive:Boolean = false
)