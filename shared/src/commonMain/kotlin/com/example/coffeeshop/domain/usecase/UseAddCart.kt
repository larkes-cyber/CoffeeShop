package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toDataCartItem
import com.example.coffeeshop.domain.model.Cart
import com.example.coffeeshop.domain.model.CartItem
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.untils.Resource

class UseAddCart(
    private val cartRepository: CartRepository
) {
    suspend fun execute(cart:CartItem):Resource<String>{
       return try {
           cartRepository.addCart(cart.toDataCartItem())
           Resource.Success("success")
        }catch (e:Exception){
            Resource.Error(e.message!!)
        }
    }
}