package com.example.coffeeshop.data.source.user

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.data.network.model.CoffeeListDto
import com.example.coffeeshop.data.network.model.UserDto
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_AUTH_USER
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString


class UserRemoteDataSourceImpl(
    private val httpClient: HttpClient
):UserRemoteDataSource {
    @OptIn(InternalAPI::class)
    override suspend fun registerUser(userDto: UserDto): UserDto {
        val response:HttpResponse = httpClient.post(POST_AUTH_USER){
            contentType(ContentType.Application.Json)
            body = userDto
        }
        return Json.decodeFromString(response.bodyAsText())
    }

    override suspend fun authUser(loginDto: UserDto): UserDto {
        TODO("Not yet implemented")
    }

    override suspend fun editUser(dataUser: DataUser) {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteCoffee(coffeeDto: CoffeeDto) {
        TODO("Not yet implemented")
    }
}