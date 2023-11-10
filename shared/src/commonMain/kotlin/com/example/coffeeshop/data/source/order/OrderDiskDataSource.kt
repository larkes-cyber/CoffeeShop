package com.example.coffeeshop.data.source.order

import com.example.coffeeshop.data.model.DataOrder

interface OrderDiskDataSource {

    suspend fun insertOrder(dataOrder: DataOrder)
    suspend fun getOrders():List<DataOrder>
    suspend fun cleanUpOrders()

}