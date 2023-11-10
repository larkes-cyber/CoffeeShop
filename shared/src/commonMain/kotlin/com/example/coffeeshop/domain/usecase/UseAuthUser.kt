package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseAuthUser(
    private val userRepository: UserRepository
) {

    suspend fun execute(login:String, password:String): Resource<String> {
        return try {
            userRepository.authUser(login = login, password = password)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}