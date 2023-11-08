package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataOrder
import com.example.coffeeshop.data.network.model.OrderDto
import com.example.coffeeshop.domain.model.Order

fun Order.toDataOrder():DataOrder{
    return DataOrder(
        id = id,
        status = status,
        orderPrice = orderPrice,
        productIds = productIds.joinToString(";"),
        date = date
    )
}

fun DataOrder.toOrder():Order{
    return Order(
        id = id,
        status = status,
        orderPrice = orderPrice,
        productIds = productIds.split(";"),
        date = date
    )
}

fun OrderDto.toDataOrder():DataOrder{
    return DataOrder(
        id = id!!,
        status = status,
        orderPrice = orderPrice,
        productIds = productIds.joinToString(";"),
        date = date
    )
}