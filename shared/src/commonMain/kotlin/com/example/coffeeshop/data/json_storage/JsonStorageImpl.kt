package com.example.coffeeshop.data.json_storage

import com.russhwolf.settings.Settings

class JsonStorageImpl(
    private val setting:Settings
):JsonStorage {

    override suspend fun putItemsToStorage(key:String, items:String){
        setting.putString(key, items)
    }

    override suspend fun getItemsByKey(key:String):String = setting.getString(key, "null")

}