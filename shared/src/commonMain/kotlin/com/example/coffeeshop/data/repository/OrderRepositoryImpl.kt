package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataOrder
import com.example.coffeeshop.data.model.DataPayment
import com.example.coffeeshop.domain.repository.OrderRepository

class OrderRepositoryImpl:OrderRepository {
    override suspend fun makePayment(payment: DataPayment) {
        TODO("Not yet implemented")
    }

    override suspend fun getOrders(): List<DataOrder> {
        TODO("Not yet implemented")
    }

    override suspend fun syncOrders() {
        TODO("Not yet implemented")
    }
}