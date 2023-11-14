package com.example.coffeeshop.domain.model

data class User(
    val name:String,
    val number:String? = null,
    val login:String? = null,
    val password:String? = null
)