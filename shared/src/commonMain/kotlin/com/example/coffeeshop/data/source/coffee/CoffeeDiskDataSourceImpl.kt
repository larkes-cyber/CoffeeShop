package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.json_storage.model.CoffeeItemsModel
import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.untils.Constants.COFFEE_JSON_FILED
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class CoffeeDiskDataSourceImpl(
    private val jsonStorage: JsonStorage
):CoffeeDiskDataSource {
    override suspend fun insertCoffee(dataCoffee: DataCoffee) {
        val lastItems = jsonStorage.getItemsByKey(COFFEE_JSON_FILED)
        val coffeeList = if(lastItems.isNotEmpty()) Json.decodeFromString(CoffeeItemsModel.serializer(), lastItems).list.toMutableList() else mutableListOf()
        coffeeList.add(dataCoffee)
        val jsonString = Json.encodeToString(CoffeeItemsModel.serializer(), CoffeeItemsModel(coffeeList))

        jsonStorage.putItemsToStorage(COFFEE_JSON_FILED, jsonString)
    }

    override suspend fun getCoffee(): List<DataCoffee> {
        TODO("Not yet implemented")
    }

    override suspend fun cleanCoffeeList() {
        TODO("Not yet implemented")
    }
}