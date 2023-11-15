package com.example.coffeeshop.android.screen.main_screen

import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.CoffeeCategory

data class MainScreenUIState(
    val isCategoriesLoading:Boolean = false,
    val isCoffeeLoading:Boolean = false,
    val categories:List<CoffeeCategory> = listOf(),
    val coffee:List<Coffee> = listOf(),
    val selectedCategory:String = ""
)