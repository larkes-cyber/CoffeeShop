package com.example.coffeeshop.data.network.model

import com.example.coffeeshop.data.network.model.OrderDto
import kotlinx.serialization.Serializable

@Serializable
data class OrderList(
    val orderList:List<OrderDto>
)