package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryListDto(
    val categoryList:List<CategoryDto>
)