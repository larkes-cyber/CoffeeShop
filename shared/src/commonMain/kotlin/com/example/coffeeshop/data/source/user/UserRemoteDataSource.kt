package com.example.coffeeshop.data.source.user

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.UserDto

interface UserRemoteDataSource {

    suspend fun registerUser(userDto: UserDto):UserDto
    suspend fun authUser(loginDto: UserDto):UserDto
    suspend fun editUser(dataUser: DataUser)
    suspend fun addFavoriteCoffee(dataCoffee: DataCoffee)

}