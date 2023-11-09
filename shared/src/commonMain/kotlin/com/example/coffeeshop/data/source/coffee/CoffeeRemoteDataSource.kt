package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.network.model.CategoryDto
import com.example.coffeeshop.data.network.model.CoffeeDto

interface CoffeeRemoteDataSource {
    suspend fun getAllCoffee():List<CoffeeDto>
    suspend fun getAllCoffeeCategory():List<CategoryDto>
}