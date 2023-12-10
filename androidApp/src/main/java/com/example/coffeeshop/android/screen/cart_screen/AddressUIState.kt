package com.example.coffeeshop.android.screen.cart_screen

data class AddressUIState(
    val showingMap:Boolean = false,
    val selectedAddress:String = "",
    val isLoading:Boolean = false
)