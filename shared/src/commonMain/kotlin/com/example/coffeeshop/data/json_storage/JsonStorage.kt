package com.example.coffeeshop.data.json_storage

interface JsonStorage {
    suspend fun putItemsToStorage(key:String, items:String)
    suspend fun getItemsByKey(key:String):String

}