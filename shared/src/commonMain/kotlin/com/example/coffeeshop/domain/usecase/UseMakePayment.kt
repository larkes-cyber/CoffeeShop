package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toDataPayment
import com.example.coffeeshop.domain.mapper.toOrder
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.model.Payment
import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.untils.Resource

class UseMakePayment(
    private val orderRepository: OrderRepository
) {

    suspend fun execute(payment: Payment): Resource<String> {
        return try {
            orderRepository.makePayment(payment.toDataPayment())
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}