package com.example.coffeeshop.android.screen.login_screen

import com.example.coffeeshop.android.untils.Constants.REGISTRATION_MODE

data class LoginUIState(
    val login:String = "",
    val password:String = "",
    val name:String = "",
    val mode:Boolean = REGISTRATION_MODE,
    val error:String = "",
    val hasBeenDone:Boolean = false
)