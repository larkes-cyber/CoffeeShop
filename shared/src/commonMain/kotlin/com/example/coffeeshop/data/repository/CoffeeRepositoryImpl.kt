package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataCoffeeCategory
import com.example.coffeeshop.domain.repository.CoffeeRepository

class CoffeeRepositoryImpl:CoffeeRepository {
    override suspend fun syncCoffee() {
        TODO("Not yet implemented")
    }

    override suspend fun syncCoffeeCategory() {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeByCategory(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeDetail(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeCategories(): List<DataCoffeeCategory> {
        TODO("Not yet implemented")
    }

    override suspend fun searchForCoffee(): List<DataCoffee> {
        TODO("Not yet implemented")
    }
}