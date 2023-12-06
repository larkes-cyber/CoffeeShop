package com.example.coffeeshop.data.source.user
import com.example.coffeeshop.data.network.model.FavoriteCoffeeDto
import com.example.coffeeshop.data.network.model.LoginDto
import com.example.coffeeshop.data.network.model.UserDto
import com.example.coffeeshop.untils.Constants.COFFEE_API

interface UserRemoteDataSource {

    suspend fun registerUser(userDto: UserDto):UserDto
    suspend fun authUser(loginDto: LoginDto):UserDto
    suspend fun editUser(dataUser: UserDto)
    suspend fun addFavoriteCoffee(coffeeDto: FavoriteCoffeeDto)
    suspend fun getUserInfo(session:String):UserDto
    suspend fun removeFavoriteCoffee(favoriteCoffeeDto: FavoriteCoffeeDto)
    suspend fun uploadProfileImage(userId:String, file:ByteArray)
    suspend fun getAddress(points:Pair<Float, Float>):String?

    companion object{

        val POST_AUTH_USER = "$COFFEE_API/user/auth"
        val POST_REGISTER_USER = "$COFFEE_API/user/register"
        val POST_EDIT_USER = "$COFFEE_API/user/edit_profile"
        val POST_ADD_FAVORITE_COFFEE = "$COFFEE_API/user/add_favorite_coffee"
        val POST_GET_USER_INFO = "$COFFEE_API/user/get_user_info"
        val POST_REMOVE_USER_INFO = "$COFFEE_API/user/remove_favorite_coffee"
        val POST_UPLOAD_PHOTO = "$COFFEE_API/user/upload_user_photo"
        val GET_ADDRESS = "COFFEE_API/user/get_address"

    }

}