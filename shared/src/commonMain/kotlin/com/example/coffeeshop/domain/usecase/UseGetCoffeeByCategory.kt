package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffee
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseGetCoffeeByCategory(
    private val coffeeRepository: CoffeeRepository,
    private val cartRepository: CartRepository
) {

    suspend fun execute(categoryId:String): Resource<List<Coffee>> {
        return try {
            Resource.Success(coffeeRepository.getCoffeeByCategory(categoryId).map { it.toCoffee() })
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}