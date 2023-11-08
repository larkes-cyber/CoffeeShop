package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val login:String,
    val password:String
)