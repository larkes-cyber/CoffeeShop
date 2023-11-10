package com.example.coffeeshop.data.source.cart

import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.data.model.DataOrder

interface CartDiskDataSource {

    suspend fun insertCart(dataCartItem: DataCartItem)
    suspend fun getCarts():List<DataCartItem>
    suspend fun removeCart(dataCartItem: DataCartItem)

}