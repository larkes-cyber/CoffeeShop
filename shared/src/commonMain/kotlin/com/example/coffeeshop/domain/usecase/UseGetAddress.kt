package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.logInTerminal
import com.example.coffeeshop.untils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UseGetAddress(
    private val userRepository: UserRepository
) {

    operator fun invoke(points:Pair<Float, Float>): Flow<Resource<String>> = flow {
        try {
            val addr = userRepository.getAddress(points)
            emit(Resource.Success(addr ?: ""))
        }catch (e:Exception){

        }
    }

}