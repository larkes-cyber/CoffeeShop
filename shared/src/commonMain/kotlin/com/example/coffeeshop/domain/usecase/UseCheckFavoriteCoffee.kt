package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository


class UseCheckFavoriteCoffee(
    private val userRepository: UserRepository
) {

    suspend fun execute(coffeeId:String):Boolean{
        val favoriteCoffee = userRepository.getFavoriteCoffee()
        return favoriteCoffee.contains(coffeeId)
    }

}