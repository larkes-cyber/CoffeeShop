package com.example.coffeeshop.domain.repository

import com.example.coffeeshop.data.model.DataCartItem

interface CartRepository {

    suspend fun addCart(dataCartItem: DataCartItem)
    suspend fun getCarts():List<DataCartItem>
    suspend fun deleteCart(dataCartItem: DataCartItem)
    suspend fun getCoffeeCartAmount(id:String):Int

}