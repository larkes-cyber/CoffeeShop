package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchForCoffeeDto(
    val symbols:String,
    val session:String
)