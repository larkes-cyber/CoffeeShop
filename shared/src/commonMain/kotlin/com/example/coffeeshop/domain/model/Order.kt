package com.example.coffeeshop.domain.model

class Order(
    val id:String,
    val status:String,
    val orderPrice:Double,
    val productIds:List<String>,
    val date:String
)