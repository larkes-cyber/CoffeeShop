package com.example.coffeeshop.domain.model

data class CartItem(
    val id:Long? = null,
    val amount:Int,
    val productId:String
)