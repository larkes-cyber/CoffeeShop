package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OrderDto(
    val session:String,
    val id:String? = null,
    val status:String,
    val userId:String,
    val orderPrice:Double,
    val productIds:List<String>,
    val date:String
)