package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffee
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseSyncCoffee(
    private val coffeeRepository: CoffeeRepository
){
    suspend fun execute(): Resource<String> {
        return try {
            coffeeRepository.syncCoffee()
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }
}