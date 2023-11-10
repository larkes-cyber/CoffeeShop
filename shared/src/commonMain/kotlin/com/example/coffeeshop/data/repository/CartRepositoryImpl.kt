package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.domain.repository.CartRepository

class CartRepositoryImpl:CartRepository {
    override suspend fun addCart(dataCartItem: DataCartItem) {
        TODO("Not yet implemented")
    }

    override suspend fun getCarts(): List<DataCartItem> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCart(dataCartItem: DataCartItem) {
        TODO("Not yet implemented")
    }
}