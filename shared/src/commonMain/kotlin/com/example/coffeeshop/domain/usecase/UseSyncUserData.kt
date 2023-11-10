package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseSyncUserData(
    private val userRepository: UserRepository
) {

    suspend fun execute(): Resource<String> {
        return try {
            userRepository.syncUser()
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}