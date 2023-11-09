package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.domain.repository.UserRepository

class UserRepositoryImpl:UserRepository {
    override suspend fun registerUser(dataUser: DataUser) {
        TODO("Not yet implemented")
    }

    override suspend fun authUser(login: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addFavoriteCoffee(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun syncUser() {
        TODO("Not yet implemented")
    }

    override suspend fun editUser(dataUser: DataUser) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavoriteCoffee(id: String) {
        TODO("Not yet implemented")
    }
}