package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCoffee
import com.example.coffeeshop.domain.mapper.toCoffeeCategory
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.CoffeeCategory
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.logInTerminal
import com.example.coffeeshop.untils.Resource

class UseGetFavoriteCoffee(
    private val userRepository: UserRepository,
    private val coffeeRepository: CoffeeRepository,
    private val cartRepository: CartRepository
) {

    suspend fun execute(): Resource<List<Coffee>> {
        return try {
            val favoriteCoffee = userRepository.getFavoriteCoffee().map {id ->
                coffeeRepository.getCoffeeDetail(id).toCoffee()
            }
            Resource.Success(favoriteCoffee)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

}