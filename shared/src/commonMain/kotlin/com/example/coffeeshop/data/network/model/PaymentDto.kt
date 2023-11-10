package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
class PaymentDto(
    val session:String,
    val products:List<String>,
    val price:Float
)