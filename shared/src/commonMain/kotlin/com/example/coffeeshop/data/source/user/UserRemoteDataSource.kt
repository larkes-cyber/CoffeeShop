package com.example.coffeeshop.data.source.user
import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.data.network.model.UserDto
import com.example.coffeeshop.untils.Constants.COFFEE_API

interface UserRemoteDataSource {

    suspend fun registerUser(userDto: UserDto):UserDto
    suspend fun authUser(loginDto: UserDto):UserDto
    suspend fun editUser(dataUser: DataUser)
    suspend fun addFavoriteCoffee(coffeeDto: CoffeeDto)

    companion object{

        val POST_AUTH_USER = "$COFFEE_API/user/auth"
        val POST_REGISTER_USER = "$COFFEE_API/user/register"
        val POST_EDIT_USER = "$COFFEE_API/user/edit_profile"
        val POST_ADD_FAVORITE_COFFEE = "$COFFEE_API/user/add_favorite_coffee"


    }

}