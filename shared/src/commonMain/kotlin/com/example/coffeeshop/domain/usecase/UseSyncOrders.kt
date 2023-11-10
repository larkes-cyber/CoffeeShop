package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.untils.Resource

class UseSyncOrders(
    private val orderRepository: OrderRepository
) {
    suspend fun execute(): Resource<String> {
        return try {
            orderRepository.syncOrders()
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }
}