package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toCartItem
import com.example.coffeeshop.domain.model.CartItem
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.untils.Resource

class UseGetCarts(
    private val cartRepository: CartRepository
) {

    suspend fun execute(): Resource<List<CartItem>> {
        return try {
            Resource.Success(cartRepository.getCarts().map { it.toCartItem() })
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }

}