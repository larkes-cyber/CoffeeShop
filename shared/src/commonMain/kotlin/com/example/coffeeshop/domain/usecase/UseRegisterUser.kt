package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toDataUser
import com.example.coffeeshop.domain.mapper.toUser
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseRegisterUser(
    private val userRepository: UserRepository
) {
    suspend fun execute(user: User): Resource<String> {
        return try {
            userRepository.registerUser(user.toDataUser())
            Resource.Success("success")
        }catch (e:Exception){
            println(e)
            Resource.Error(e.message!!)
        }
    }

}