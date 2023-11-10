package com.example.coffeeshop.data.source.order

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.json_storage.model.CoffeeItemsModel
import com.example.coffeeshop.data.json_storage.model.OrderItemsModel
import com.example.coffeeshop.data.model.DataOrder
import com.example.coffeeshop.untils.Constants
import com.example.coffeeshop.untils.Constants.ORDER_JSON_FIELD
import kotlinx.serialization.json.Json

class OrderDiskDataSourceImpl(
    private val jsonStorage: JsonStorage
):OrderDiskDataSource {
    override suspend fun insertOrder(dataOrder: DataOrder) {
        val orderList = getOrders()
        orderList.add(dataOrder)
        val jsonString = Json.encodeToString(OrderItemsModel.serializer(), OrderItemsModel(orderList))
        jsonStorage.putItemsToStorage(ORDER_JSON_FIELD, jsonString)
    }

    override suspend fun getOrders(): MutableList<DataOrder> {
        val orderList = jsonStorage.getItemsByKey(ORDER_JSON_FIELD)
        return if (orderList.isNotEmpty()) Json.decodeFromString(
            OrderItemsModel.serializer(),
            orderList
        ).list.toMutableList() else mutableListOf()
    }

    override suspend fun cleanOrders() {
        jsonStorage.putItemsToStorage(Constants.ORDER_JSON_FIELD, "")
    }
}