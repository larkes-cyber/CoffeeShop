package com.example.coffeeshop.android.screen.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import com.example.coffeeshop.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun onDone(){
        viewModelScope.launch {

            val res = UseCases.useRegisterUser().execute(
                User(
                    login = loginUIState.value.login,
                    password = loginUIState.value.password,
                    name = loginUIState.value.name
                )
            )
            if(res.message != null){
                _loginUIState.value = loginUIState.value.copy(error = res.message!!)
            }else{
                _loginUIState.value = loginUIState.value.copy(hasBeenDone = true)
            }


        }
    }
}