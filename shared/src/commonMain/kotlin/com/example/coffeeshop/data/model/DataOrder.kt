package com.example.coffeeshop.data.model

data class DataOrder(
    val id:String,
    val status:String,
    val orderPrice:Double,
    val productIds:String,
    val date:String
)