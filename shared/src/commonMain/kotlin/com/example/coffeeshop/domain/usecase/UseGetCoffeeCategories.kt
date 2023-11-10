package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffeeCategory
import com.example.coffeeshop.domain.model.CoffeeCategory
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseGetCoffeeCategories(
    private val coffeeRepository: CoffeeRepository
) {
    suspend fun execute(): Resource<List<CoffeeCategory>> {
        return try {
            Resource.Success(coffeeRepository.getCoffeeCategories().map { it.toCoffeeCategory() })
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }
}