package com.example.coffeeshop.android

import android.app.Application
import android.util.Log
import com.example.coffeeshop.BusinessModule
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application:Application(){

    init {
        BusinessModule(this).init()
        MapKitFactory.setApiKey("848aefe2-e81e-4b91-b0ea-b006e36e9c52")
    }

}