package com.example.coffeeshop.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DataUser(
    val name:String,
    val photoSrc:String?,
    val number:String?,
    val favoriteCoffee:String?,
    val session:String?,
    val login:String?,
    val password:String?
)