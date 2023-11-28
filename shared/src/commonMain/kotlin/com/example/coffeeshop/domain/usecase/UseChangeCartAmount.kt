package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.domain.repository.CartRepository

class UseChangeCartAmount(
    private val cartRepository: CartRepository
) {

    suspend fun execute(id:String, amount:Int){
        val cart = cartRepository.getCarts().find { it.productId == id }
        if(cart!!.amount + amount == 0) {
            cartRepository.deleteCart(cart)
            return
        }
        cart.amount += amount
        cartRepository.addCart(cart)
    }

}