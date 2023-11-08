package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataCoffeeCategory
import com.example.coffeeshop.data.network.model.CategoryDto
import com.example.coffeeshop.domain.model.CoffeeCategory

fun CoffeeCategory.toDataCoffeeCategory():DataCoffeeCategory{
    return DataCoffeeCategory(
        id = id,
        title = title
    )
}

fun DataCoffeeCategory.toCoffeeCategory():CoffeeCategory{
    return CoffeeCategory(
        id = id,
        title = title
    )
}

fun CategoryDto.toDataCoffeeCategory():DataCoffeeCategory{
    return DataCoffeeCategory(
        id = id!!,
        title = title
    )
}