package com.example.coffeeshop.data.json_storage.model

import com.example.coffeeshop.data.model.DataCoffee
import kotlinx.serialization.Serializable

@Serializable
class CoffeeItemsModel(
    val list:List<DataCoffee>
)