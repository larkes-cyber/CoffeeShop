package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.network.model.CategoryDto
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.data.network.model.CoffeeListDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoffeeRemoteDataSourceImpl(
    private val httpClient: HttpClient
):CoffeeRemoteDataSource {
    override suspend fun getAllCoffee(): List<CoffeeDto> {
        val response = httpClient.get<CoffeeListDto>()
    }

    override suspend fun getAllCoffeeCategory(): List<CategoryDto> {
        TODO("Not yet implemented")
    }
}