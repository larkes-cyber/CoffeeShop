package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toDataUser
import com.example.coffeeshop.domain.model.User
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource

class UseEditUser(
    private val userRepository: UserRepository
) {

    suspend fun execute(user: User):Resource<String>{
        return try {
            userRepository.editUser(user.toDataUser())
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}