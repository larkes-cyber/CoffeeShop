package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.domain.model.CartItem

fun CartItem.toDataCartItem():DataCartItem{
    return DataCartItem(
        amount = amount,
        id = id,
        productId = productId
    )
}

fun DataCartItem.toCartItem():CartItem{
    return CartItem(
        amount = amount,
        id = id,
        productId = productId
    )
}