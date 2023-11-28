package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.UserRepository

class UseDeleteUserData(
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository
) {

    suspend fun execute(){
        userRepository.deleteUser()
        cartRepository.getCarts().forEach {
            cartRepository.deleteCart(it)
        }
    }

}