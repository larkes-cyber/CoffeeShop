package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.model.DataCoffee

interface CoffeeDiskDataSource {

    suspend fun insertCoffee(dataCoffee: DataCoffee)
    suspend fun getCoffee():List<DataCoffee>
    suspend fun cleanCoffeeList()

}