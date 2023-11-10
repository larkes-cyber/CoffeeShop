package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.json_storage.model.CoffeeItemsModel
import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.untils.Constants.COFFEE_JSON_FILED
import kotlinx.serialization.json.Json

class CoffeeDiskDataSourceImpl(
    private val jsonStorage: JsonStorage
):CoffeeDiskDataSource {
    override suspend fun insertCoffee(dataCoffee: DataCoffee) {
        val coffeeList = getCoffee()
        coffeeList.add(dataCoffee)
        val jsonString = Json.encodeToString(CoffeeItemsModel.serializer(), CoffeeItemsModel(coffeeList))
        jsonStorage.putItemsToStorage(COFFEE_JSON_FILED, jsonString)
    }

    override suspend fun getCoffee(): MutableList<DataCoffee> {
        val coffeeList = jsonStorage.getItemsByKey(COFFEE_JSON_FILED)
        return if (coffeeList.isNotEmpty()) Json.decodeFromString(
            CoffeeItemsModel.serializer(),
            coffeeList
        ).list.toMutableList() else mutableListOf()
    }

    override suspend fun cleanCoffeeList() {
        jsonStorage.putItemsToStorage(COFFEE_JSON_FILED, "")
    }
}