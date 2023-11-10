package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffee
import com.example.coffeeshop.domain.mapper.toDataUser
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseSearchForCoffee(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(symbols:String): Resource<List<Coffee>> {
        return try {
            Resource.Success(coffeeRepository.searchForCoffee(symbols).map { it.toCoffee() })
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}