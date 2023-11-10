package com.example.coffeeshop.data.json_storage.model

import com.example.coffeeshop.data.model.DataOrder
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemsModel(
    val list:List<DataOrder>
)