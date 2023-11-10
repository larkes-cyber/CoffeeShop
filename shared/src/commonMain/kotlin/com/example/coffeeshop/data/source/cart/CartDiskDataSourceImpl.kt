package com.example.coffeeshop.data.source.cart

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.model.DataCartItem
import com.example.coffeeshop.data.model.DataOrder
import com.example.coffeeshop.database.CartDatabase
import com.example.coffeeshop.domain.mapper.toDataCartItem

class CartDiskDataSourceImpl(
    private val db:CartDatabase
):CartDiskDataSource {

    private val queries = db.cartQueries
    override suspend fun insertCart(dataCartItem: DataCartItem) {
       queries.insertCart(
           id = dataCartItem.id,
           amount = dataCartItem.amount.toLong(),
           productId = dataCartItem.productId
       )
    }

    override suspend fun getCarts(): List<DataCartItem> = queries.getAllCarts().executeAsList().map { it.toDataCartItem() }

    override suspend fun deleteCart(dataCartItem: DataCartItem) {
        queries.deleteCart(dataCartItem.id!!.toLong())
    }

}