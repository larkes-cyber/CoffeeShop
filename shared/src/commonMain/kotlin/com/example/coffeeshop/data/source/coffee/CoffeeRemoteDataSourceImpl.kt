package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.network.model.CategoryDto
import com.example.coffeeshop.data.network.model.CategoryListDto
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.data.source.coffee.CoffeeRemoteDataSource.Companion.POST_GET_COFFEE
import com.example.coffeeshop.data.source.coffee.CoffeeRemoteDataSource.Companion.POST_GET_COFFEE_CATEGORY
import com.example.coffeeshop.logInTerminal
import com.example.coffeeshop.untils.Constants.COFFEE_PHOTOS_URL
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readBytes
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CoffeeRemoteDataSourceImpl(
    private val httpClient: HttpClient
):CoffeeRemoteDataSource {
    override suspend fun getAllCoffee(session:String): List<CoffeeDto> {
        logInTerminal("check_coffee")
        val response = httpClient.post(POST_GET_COFFEE){
            url{
                parameters.append("session", session)
            }
        }
        logInTerminal(response.bodyAsText() + " ##############################")
        val coffee = Json.decodeFromString<List<CoffeeDto>>(response.bodyAsText())
        coffee.forEach {
            logInTerminal(it.id!!)
        }
        return coffee
    }

    override suspend fun getAllCoffeeCategory(session:String): List<CategoryDto> {
        logInTerminal("coffee_cafasfasf")
        val response = httpClient.post(POST_GET_COFFEE_CATEGORY){
            url{
                parameters.append("session", session)
            }
        }
        val category = Json.decodeFromString<CategoryListDto>(response.bodyAsText())
        logInTerminal("coffee_cafasfasf")
        category.categoryList.forEach {
            logInTerminal(it.title)
        }
        return category.categoryList
    }

    override suspend fun getCoffeeImage(id: String): ByteArray {
        val response = httpClient.get(COFFEE_PHOTOS_URL + id)
        return response.readBytes()
    }
}