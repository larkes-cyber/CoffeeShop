package com.example.coffeeshop.domain.usecase

import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.domain.mapper.toDataCartItem
import com.example.coffeeshop.domain.model.CartItem
import com.example.coffeeshop.domain.repository.CartRepository

class UseDeleteCart(
    private val cartRepository: CartRepository
) {

    suspend fun execute(productId:String){
        val cart = cartRepository.getCarts().find { it.productId == productId }
        cartRepository.deleteCart(DataCartItem(id = cart!!.id, productId = productId, amount = 0))
    }

}