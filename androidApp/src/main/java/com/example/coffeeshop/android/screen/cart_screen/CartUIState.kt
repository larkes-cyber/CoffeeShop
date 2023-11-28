package com.example.coffeeshop.android.screen.cart_screen

import com.example.coffeeshop.domain.model.Coffee

data class CartUIState(
    val coffee:List<Pair<Coffee, Int>> = listOf(),
    val isLoading:Boolean = false,
    val error:String = "",
    val totalPrice:Float = 0f
)