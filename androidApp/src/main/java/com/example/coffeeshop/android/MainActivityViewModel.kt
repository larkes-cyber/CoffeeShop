package com.example.coffeeshop.android

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.coffeeshop.BusinessModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor():ViewModel(){

    fun initKoin(context: Context){
        try {
            BusinessModule(context).init()
        }catch (e:Exception){

        }
    }

}