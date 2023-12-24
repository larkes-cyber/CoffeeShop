package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.untils.Resource
import io.ktor.utils.io.core.toByteArray

class UseUploadUserPhoto(
    private val userRepository: UserRepository
) {

    suspend fun execute(userId:String, file:ByteArray):Resource<String>{
       return try {

            userRepository.uploadUserPhoto(userId = userId, file = file)
            Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}