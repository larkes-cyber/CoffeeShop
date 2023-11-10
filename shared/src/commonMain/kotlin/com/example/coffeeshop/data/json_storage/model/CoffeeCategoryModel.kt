package com.example.coffeeshop.data.json_storage.model

import com.example.coffeeshop.data.model.DataCoffeeCategory
import kotlinx.serialization.Serializable

@Serializable
data class CoffeeCategoryModel(
    val list:List<DataCoffeeCategory>
)