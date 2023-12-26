package com.example.coffeeshop.android.screen.splash_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.android.untils.Constants.AUTH_NOT_STATED
import com.example.coffeeshop.android.untils.Constants.NOT_AUTH
import com.example.coffeeshop.android.untils.Constants.AUTH_SUCCESS
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
):ViewModel(){

    private val _authUIState = MutableStateFlow(AUTH_NOT_STATED)
    val authUIState:StateFlow<Int> = _authUIState

    fun init(){
        viewModelScope.launch {
            val user = UseCases.useGetUserData().execute().data

            Log.d("sdfsdfffddff", user.toString())

            if(user != null){
                UseCases.useSyncCoffeeCategories().execute()
                UseCases.useSyncCoffee().execute()
                UseCases.useSyncUserData().execute()
                UseCases.useSyncOrders().execute()
            }

            _authUIState.value =  if(user == null) NOT_AUTH else AUTH_SUCCESS
        }
    }

}

