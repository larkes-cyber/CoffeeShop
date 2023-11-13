package com.example.coffeeshop.android.screen.login_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel() {

    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState:StateFlow<LoginUIState> = _loginUIState

    fun switchMode(){
        _loginUIState.value = loginUIState.value.copy(mode = loginUIState.value.mode.not())
    }

    fun onLoginChange(text:String){
        _loginUIState.value = loginUIState.value.copy(login = text)
    }

    fun onPasswordChange(text:String){
        _loginUIState.value = loginUIState.value.copy(password = text)
    }

    fun onNameChange(text:String){
        _loginUIState.value = loginUIState.value.copy(name = text)
    }
}