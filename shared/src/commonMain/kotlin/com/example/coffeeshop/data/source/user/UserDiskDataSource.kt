package com.example.coffeeshop.data.source.user

import com.example.coffeeshop.data.model.DataUser

interface UserDiskDataSource {

    suspend fun putUserData(dataUser: DataUser)
    suspend fun getUserData():DataUser?


}