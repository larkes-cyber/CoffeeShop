package com.example.coffeeshop.domain.repository

import com.example.coffeeshop.data.model.DataOrder
import com.example.coffeeshop.data.model.DataPayment

interface OrderRepository {

    suspend fun makePayment(payment:DataPayment)
    suspend fun getOrders():List<DataOrder>
    suspend fun syncOrders()

}