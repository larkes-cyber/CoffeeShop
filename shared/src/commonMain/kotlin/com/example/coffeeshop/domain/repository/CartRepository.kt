package com.example.coffeeshop.domain.repository

import com.example.coffeeshop.data.model.DataCart

interface CartRepository {

    suspend fun addCart(dataCart: DataCart)
    suspend fun getCarts():List<DataCart>
    suspend fun deleteCart(dataCart: DataCart)

}