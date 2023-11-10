package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.UserDto
import com.example.coffeeshop.domain.model.User
import kotlin.math.log

fun User.toDataUser():DataUser{
    return DataUser(
        name = name,
        photoSrc = photoSrc,
        number = number,
        favoriteCoffee = null,
        session = null,
        login = null,
        password = null
    )
}

fun DataUser.toUser():User{
    return User(
        name = name,
        photoSrc = photoSrc,
        number = number
    )
}

fun UserDto.toDataUser():DataUser{
    return DataUser(
        name = name,
        photoSrc = photoSrc,
        number = number,
        favoriteCoffee = favoriteCoffee,
        session = session,
        login = login,
        password = password
    )
}

fun DataUser.toUserDto():UserDto{
    return UserDto(
        name = name,
        photoSrc = photoSrc,
        number = number,
        favoriteCoffee = favoriteCoffee ?: "",
        session = session ?: "",
        login = login!!,
        password = password!!
    )
}
