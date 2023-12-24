package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.FavoriteCoffeeDto
import com.example.coffeeshop.data.network.model.LoginDto
import com.example.coffeeshop.data.source.user.UserDiskDataSource
import com.example.coffeeshop.data.source.user.UserRemoteDataSource
import com.example.coffeeshop.domain.mapper.toDataUser
import com.example.coffeeshop.domain.mapper.toUserDto
import com.example.coffeeshop.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userDiskDataSource: UserDiskDataSource
):UserRepository {
    override suspend fun registerUser(dataUser: DataUser) {
        val user = userRemoteDataSource.registerUser(dataUser.toUserDto())
        userDiskDataSource.putUserData(user.toDataUser())
    }

    override suspend fun authUser(login: String, password: String) {
        val user = userRemoteDataSource.authUser(LoginDto(login = login, password = password))
        userDiskDataSource.putUserData(user.toDataUser())
    }

    override suspend fun addFavoriteCoffee(id: String) {
        val user = getUser()!!
        userRemoteDataSource.addFavoriteCoffee(coffeeDto = FavoriteCoffeeDto(session = user.session!!, coffeeId =  id))
        syncUser()
    }

    override suspend fun getFavoriteCoffee():List<String> = getUser()!!.favoriteCoffee!!.split(";")

    override suspend fun syncUser() {
        val user = getUser()!!
        val serverUser = userRemoteDataSource.getUserInfo(user.session!!)
        println(serverUser.toString() + "   sdfghgfdfgfddfe")
        userDiskDataSource.putUserData(serverUser.toDataUser())
    }

    override suspend fun editUser(dataUser: DataUser) {
        userDiskDataSource.putUserData(dataUser)
        userRemoteDataSource.editUser(dataUser.toUserDto())
    }

    override suspend fun removeFavoriteCoffee(id: String) {
        val user = getUser()
        val coffeeList = user!!.favoriteCoffee?.split(";")?.toMutableList() ?: return
        coffeeList.remove(id)
        user.favoriteCoffee = coffeeList.joinToString(";")
        userDiskDataSource.putUserData(user)
        userRemoteDataSource.removeFavoriteCoffee( FavoriteCoffeeDto(session = user.session!!, coffeeId =  id))
    }

    override suspend fun getUser(): DataUser? = userDiskDataSource.getUserData()
    override suspend fun uploadUserPhoto(userId: String, file: ByteArray) {
        userRemoteDataSource.uploadProfileImage(userId = userId, file = file)
    }

    override suspend fun deleteUser() {
        userDiskDataSource.deleteUser()
    }

    override suspend fun getAddress(points: Pair<Float, Float>): String? {
        return userRemoteDataSource.getAddress(points)
    }
}