package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.mapper.toDataCartItem
import com.example.coffeeshop.domain.model.Cart
import com.example.coffeeshop.domain.model.CartItem
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.untils.Resource

class UseAddCart(
    private val cartRepository: CartRepository,
    private val useChangeCartAmount: UseChangeCartAmount
) {
    suspend fun execute(cart:CartItem):Resource<String>{
        try {
            val coffee = cartRepository.getCarts().find { it.productId == cart.productId }
            if(coffee != null){
                useChangeCartAmount.execute(cart.productId, cart.amount)
                return Resource.Success("success")
            }
            cartRepository.addCart(cart.toDataCartItem())
            return Resource.Success("success")
        }catch (e:Exception){
            return Resource.Error(e.message!!)
        }
    }
}