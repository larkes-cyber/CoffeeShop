package com.example.coffeeshop.android.screen.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.android.untils.Constants.AUTH_NOT_STATED
import com.example.coffeeshop.android.untils.Constants.HASNT_AUTH
import com.example.coffeeshop.android.untils.Constants.HAS_AUTH
import com.example.coffeeshop.di.UseCases
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.domain.usecase.UseGetUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useGetUserData:UseGetUserData = UseCases.useGetUserData()
):ViewModel(){

    private val _hasBeenAuth = MutableStateFlow(AUTH_NOT_STATED)
    val hasBeenAuth:StateFlow<Int> = _hasBeenAuth
    init {
        viewModelScope.launch {
           val user = useGetUserData.execute().data
            _hasBeenAuth.value =  if(user == null) HASNT_AUTH else HAS_AUTH

        }
    }

}