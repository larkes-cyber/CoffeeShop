package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.network.model.CategoryDto
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.untils.Constants.COFFEE_API

interface CoffeeRemoteDataSource {
    suspend fun getAllCoffee(session:String):List<CoffeeDto>
    suspend fun getAllCoffeeCategory(session: String):List<CategoryDto>
    companion object{
        val POST_GET_COFFEE = "$COFFEE_API/coffee/get_all_coffee"
        val POST_GET_COFFEE_CATEGORY = "$COFFEE_API/coffee_category/get_categories"
    }
}