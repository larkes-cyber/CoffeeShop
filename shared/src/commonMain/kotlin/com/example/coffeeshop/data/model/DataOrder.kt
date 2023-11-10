package com.example.coffeeshop.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DataOrder(
    val id:String,
    val status:String,
    val orderPrice:Double,
    val productIds:String,
    val date:String
)