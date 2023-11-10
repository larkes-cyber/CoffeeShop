package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffee
import com.example.coffeeshop.domain.mapper.toOrder
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.untils.Resource

class UseGetOrders(
    private val orderRepository: OrderRepository
) {

    suspend fun execute(): Resource<List<Order>> {
        return try {
            Resource.Success(orderRepository.getOrders().map { it.toOrder() })
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}