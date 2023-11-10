package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataCoffeeCategory

interface CoffeeDiskDataSource {

    suspend fun insertCoffee(dataCoffee: DataCoffee)
    suspend fun getCoffee():List<DataCoffee>
    suspend fun cleanUpCoffee()
    suspend fun insertCoffeeCategory(dataCoffeeCategory: DataCoffeeCategory)
    suspend fun cleanUpCoffeeCategory()
    suspend fun getCoffeeCategories():List<DataCoffeeCategory>
}