package com.example.coffeeshop.data.source.cart

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.data.model.DataOrder

class CartDiskDataSourceImpl(
    private val jsonStorage: JsonStorage
):CartDiskDataSource {
    override suspend fun insertCart(dataCartItem: DataCartItem) {
       // jsonStorage.putItemsToStorage()
    }

    override suspend fun getCarts(): List<DataCartItem> {
        TODO("Not yet implemented")
    }

    override suspend fun removeCart(dataCartItem: DataCartItem) {
        TODO("Not yet implemented")
    }

}