package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id:String? = null,
    val title:String,
    val session:String
)