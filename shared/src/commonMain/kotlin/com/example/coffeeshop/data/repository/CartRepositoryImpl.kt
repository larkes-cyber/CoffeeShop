package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.data.source.cart.CartDiskDataSource
import com.example.coffeeshop.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartDiskDataSource: CartDiskDataSource
):CartRepository {
    override suspend fun addCart(dataCartItem: DataCartItem) {
        cartDiskDataSource.insertCart(dataCartItem)
    }

    override suspend fun getCarts(): List<DataCartItem> {
       return cartDiskDataSource.getCarts()
    }

    override suspend fun deleteCart(dataCartItem: DataCartItem) {
        cartDiskDataSource.deleteCart(dataCartItem)
    }
}