package com.example.coffeeshop.data.source.cart

import com.example.coffeeshop.data.model.DataCartItem

interface CartDiskDataSource {

    suspend fun insertCart(dataCartItem: DataCartItem)
    suspend fun getCarts():List<DataCartItem>
    suspend fun deleteCart(dataCartItem: DataCartItem)


}