package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toDataCartItem
import com.example.coffeeshop.domain.model.CartItem
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.logInTerminal
import com.example.coffeeshop.untils.Resource

class UseAddFavoriteCoffee(
    private val userRepository: UserRepository
) {

    suspend fun execute(id:String): Resource<String> {
        return try {
            userRepository.addFavoriteCoffee(id)
            Resource.Success("success")
        }catch (e:Exception){
            logInTerminal(e.toString())
            Resource.Error(e.message!!)
        }
    }

}