package com.example.coffeeshop.data.model

data class DataPayment(
    val session:String,
    val products:List<String>,
    val price:Float
)