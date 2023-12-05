package com.example.coffeeshop.domain.repository

import com.example.coffeeshop.data.model.DataUser

interface UserRepository {

    suspend fun registerUser(dataUser: DataUser)
    suspend fun authUser(login:String, password:String)
    suspend fun addFavoriteCoffee(id:String)
    suspend fun getFavoriteCoffee():List<String>
    suspend fun syncUser()
    suspend fun editUser(dataUser: DataUser)
    suspend fun removeFavoriteCoffee(id:String)
    suspend fun getUser():DataUser?
    suspend fun uploadUserPhoto(userId:String, file:ByteArray)
    suspend fun deleteUser()
    suspend fun getAddress(points:Pair<Float, Float>):String?

}