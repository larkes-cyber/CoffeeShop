package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataCart
import com.example.coffeeshop.domain.repository.CartRepository

class CartRepositoryImpl:CartRepository {
    override suspend fun addCart(dataCart: DataCart) {
        TODO("Not yet implemented")
    }

    override suspend fun getCarts(): List<DataCart> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCart(dataCart: DataCart) {
        TODO("Not yet implemented")
    }
}