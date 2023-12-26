package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseFullAppSync(
    private val userRepository: UserRepository,
    private val coffeeRepository: CoffeeRepository,
    private val orderRepository: OrderRepository
) {

    suspend fun execute():Resource<String>{
        return try {
            userRepository.syncUser()
            coffeeRepository.syncCoffeeCategory()
            coffeeRepository.syncCoffee()
            orderRepository.syncOrders()
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Success("success")
        }
    }

}