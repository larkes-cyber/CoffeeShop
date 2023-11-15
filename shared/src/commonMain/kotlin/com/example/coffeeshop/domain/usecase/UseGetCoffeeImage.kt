package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.untils.Resource

class UseGetCoffeeImage(
    private val coffeeRepository: CoffeeRepository
) {

    suspend fun execute(id:String):Resource<ByteArray>{
        return try {
            val image = coffeeRepository.getCoffeeImage(id)
            Resource.Success(image)
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}