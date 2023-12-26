package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.UserDto
import com.example.coffeeshop.domain.model.User
import kotlin.math.log

fun User.toDataUser(
    session:String? = null,
    favoriteCoffee:String? = null
):DataUser{
    return DataUser(
        name = name,
        number = number,
        favoriteCoffee = favoriteCoffee,
        session = session,
        login = login,
        password = password
    )
}

fun DataUser.toUser():User{
    return User(
        name = name,
        number = number,
        login = login,
        password = password
    )
}

fun UserDto.toDataUser():DataUser{
    return DataUser(
        name = name,
        number = number,
        favoriteCoffee = favoriteCoffee,
        login = login,
        password = password,
        session = id
    )
}

fun DataUser.toUserDto():UserDto{
    return UserDto(
        id = session,
        name = name,
        number = number,
        favoriteCoffee = favoriteCoffee ?: "",
        login = login!!,
        password = password!!
    )
}
