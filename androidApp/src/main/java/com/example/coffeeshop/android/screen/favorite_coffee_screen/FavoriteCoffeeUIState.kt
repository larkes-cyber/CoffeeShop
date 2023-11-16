package com.example.coffeeshop.android.screen.favorite_coffee_screen

import com.example.coffeeshop.domain.model.Coffee

data class FavoriteCoffeeUIState(
    val isLoading:Boolean = false,
    val coffee:List<Coffee> = listOf(),
    val error:String = ""
)