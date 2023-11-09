package com.example.coffeeshop.data.source.cart

import com.example.coffeeshop.data.model.DataOrder

interface CartDiskDataSource {

    suspend fun insertOrder(dataOrder: DataOrder)
    suspend fun getOrders():List<DataOrder>
    suspend fun removeOrder(dataOrder: DataOrder)

}