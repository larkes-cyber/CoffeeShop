package com.example.coffeeshop.data.source.order

import com.example.coffeeshop.data.network.model.OrderDto
import com.example.coffeeshop.data.network.model.PaymentDto
import com.example.coffeeshop.untils.Constants.COFFEE_API

interface OrderRemoteDataSource {
    suspend fun sendPayment(paymentDto: PaymentDto)
    suspend fun getOrders(session:String):List<OrderDto>

    companion object{
        val POST_GET_USER_ORDERS = "$COFFEE_API/order/get_user_orders"
        val POST_ADD_USER_ORDER = "$COFFEE_API/order/add_order"
    }

}