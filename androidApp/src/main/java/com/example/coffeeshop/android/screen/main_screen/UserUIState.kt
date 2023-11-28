package com.example.coffeeshop.android.screen.main_screen

import com.example.coffeeshop.domain.model.User

data class UserUIState(
    val isLoading:Boolean = false,
    val user:User? = null
)