package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.logInTerminal
import com.example.coffeeshop.untils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetAddress(
    private val userRepository: UserRepository
) {

   suspend fun execute(points:Pair<Float, Float>):Resource<String>{
       return try {
           val addr = userRepository.getAddress(points) ?: ""
           Resource.Success(addr)
       }catch (e:Exception){
           logInTerminal(e.toString())
           Resource.Error(e.message.toString())
       }
   }

}