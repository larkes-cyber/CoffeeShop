package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.model.DataCoffee

class CoffeeDiskDataSourceImpl:CoffeeDiskDataSource {
    override suspend fun insertCoffee(dataCoffee: DataCoffee) {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffee(): List<DataCoffee> {
        TODO("Not yet implemented")
    }

    override suspend fun cleanCoffeeList() {
        TODO("Not yet implemented")
    }
}