package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffee
import com.example.coffeeshop.domain.mapper.toCoffeeCategory
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.CoffeeCategory
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseGetCoffeeDetailById(
    private val coffeeRepository: CoffeeRepository,
    private val cartRepository: CartRepository
) {

    suspend fun execute(id:String): Resource<Coffee> {
        return try {
            Resource.Success(coffeeRepository.getCoffeeDetail(id).toCoffee(cartRepository.getCoffeeCartAmount(id) == 0))
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}