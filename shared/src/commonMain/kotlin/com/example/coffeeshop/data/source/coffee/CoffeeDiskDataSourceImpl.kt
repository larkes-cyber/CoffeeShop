package com.example.coffeeshop.data.source.coffee

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.json_storage.model.CoffeeCategoryModel
import com.example.coffeeshop.data.json_storage.model.CoffeeItemsModel
import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataCoffeeCategory
import com.example.coffeeshop.untils.Constants.COFFEE_CATEGORY_FILED
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

    override suspend fun cleanUpCoffee() {
        jsonStorage.putItemsToStorage(COFFEE_JSON_FILED, "")
    }

    override suspend fun insertCoffeeCategory(dataCoffeeCategory: DataCoffeeCategory) {
        val coffeeList = getCoffeeCategories()
        coffeeList.add(dataCoffeeCategory)
        val jsonString = Json.encodeToString(CoffeeCategoryModel.serializer(), CoffeeCategoryModel(coffeeList))
        jsonStorage.putItemsToStorage(COFFEE_CATEGORY_FILED, jsonString)
    }

    override suspend fun cleanUpCoffeeCategory() {
        jsonStorage.putItemsToStorage(COFFEE_CATEGORY_FILED, "")
    }

    override suspend fun getCoffeeCategories():MutableList<DataCoffeeCategory> {
        val coffeeList = jsonStorage.getItemsByKey(COFFEE_CATEGORY_FILED)
        return if (coffeeList.isNotEmpty()) Json.decodeFromString(
            CoffeeCategoryModel.serializer(),
            coffeeList
        ).list.toMutableList() else mutableListOf()
    }
}