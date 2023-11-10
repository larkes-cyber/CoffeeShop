package com.example.coffeeshop.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DataCoffeeCategory(
    val id:String,
    val title:String
)