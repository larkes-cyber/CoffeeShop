package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseRemoveFavoriteCoffee(
    private val userRepository: UserRepository
) {

    suspend fun execute(id:String): Resource<String> {
        return try {
            userRepository.removeFavoriteCoffee(id)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}