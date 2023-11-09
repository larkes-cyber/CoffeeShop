package com.example.coffeeshop.domain.repository

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataCoffeeCategory

interface CoffeeRepository {

    suspend fun syncCoffee()
    suspend fun syncCoffeeCategory()
    suspend fun getCoffeeByCategory(id:String)
    suspend fun getCoffeeDetail(id:String)
    suspend fun getCoffeeCategories():List<DataCoffeeCategory>
    suspend fun searchForCoffee():List<DataCoffee>

}