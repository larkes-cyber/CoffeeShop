package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataOrder
import com.example.coffeeshop.data.model.DataPayment
import com.example.coffeeshop.data.network.model.PaymentDto
import com.example.coffeeshop.data.source.order.OrderDiskDataSource
import com.example.coffeeshop.data.source.order.OrderRemoteDataSource
import com.example.coffeeshop.domain.mapper.toDataOrder
import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.domain.repository.UserRepository

class OrderRepositoryImpl(
    private val orderDiskDataSource: OrderDiskDataSource,
    private val orderRemoteDataSource: OrderRemoteDataSource,
    private val userRepository: UserRepository
):OrderRepository {
    override suspend fun makePayment(payment: DataPayment) {
        val user = userRepository.getUser()!!
        orderRemoteDataSource.sendPayment(PaymentDto(
            session = user.session!!,
            products = payment.products,
            price = payment.price
        ))
        syncOrders()
    }

    override suspend fun getOrders(): List<DataOrder> {
        val session = userRepository.getUser()!!.session!!
        return orderRemoteDataSource.getOrders(session).map { it.toDataOrder() }
    }

    override suspend fun syncOrders() {
        val orders = getOrders()
        orderDiskDataSource.cleanUpOrders()
        orders.forEach {
            orderDiskDataSource.insertOrder(it)
        }
    }
}