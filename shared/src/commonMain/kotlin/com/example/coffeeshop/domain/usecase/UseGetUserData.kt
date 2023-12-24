package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toOrder
import com.example.coffeeshop.domain.mapper.toUser
import com.example.coffeeshop.domain.model.Order
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseGetUserData(
    private val userRepository:UserRepository
) {

    suspend fun execute(): Resource<User?> {
        return try {
            val user = userRepository.getUser()
            println(user.toString() + "@@@@@@@@@@@@@@")
            Resource.Success(userRepository.getUser()?.toUser())
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}