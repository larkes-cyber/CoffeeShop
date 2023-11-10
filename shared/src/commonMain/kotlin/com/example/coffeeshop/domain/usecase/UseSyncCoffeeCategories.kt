package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseSyncCoffeeCategories(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(): Resource<String> {
        return try {
            coffeeRepository.syncCoffeeCategory()
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}