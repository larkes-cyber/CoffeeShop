package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CartRepository

class UseGetCoffeeCartAmount(
    private val cartRepository: CartRepository
) {

    suspend fun execute(id:String):Int{
        return cartRepository.getCoffeeCartAmount(id)
    }

}