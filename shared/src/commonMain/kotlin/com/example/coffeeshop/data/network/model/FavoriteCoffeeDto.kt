package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCoffeeDto(
    val session:String,
    val coffeeId:String
)