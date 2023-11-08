package com.example.coffeeshop.data.network.model

import com.example.coffeeshop.data.network.model.CoffeeDto
import kotlinx.serialization.Serializable

@Serializable
data class CoffeeListDto(
    val coffeeList:List<CoffeeDto>
)