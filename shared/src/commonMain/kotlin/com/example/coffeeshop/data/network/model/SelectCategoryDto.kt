package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SelectCategoryDto(
    val session:String,
    val category:String
)