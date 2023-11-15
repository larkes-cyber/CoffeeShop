package com.example.coffeeshop.android.screen.coffee_detail_screen

import androidx.compose.ui.graphics.ImageBitmap
import com.example.coffeeshop.domain.model.Coffee

data class CoffeeDetailUIState(
    val isLoading:Boolean = false,
    val coffee:Coffee? = null,
    val error:String = "",
    val image:ImageBitmap? = null
)