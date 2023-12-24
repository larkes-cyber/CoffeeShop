package com.example.coffeeshop.data.source.user

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.untils.Constants.USER_JSON_FILED
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.encodeToString

class UserDiskDataSourceImpl(
    private val jsonStorage: JsonStorage
):UserDiskDataSource {
    override suspend fun putUserData(dataUser: DataUser) {
        val parsedUser = encodeToString(DataUser.serializer(), dataUser)
        jsonStorage.putItemsToStorage(USER_JSON_FILED, parsedUser)
    }

    override suspend fun getUserData(): DataUser? {
        val data = jsonStorage.getItemsByKey(USER_JSON_FILED)
        println((if(data.isNotEmpty()) Json.decodeFromString(DataUser.serializer(), data) else null).toString() + "dfgvsxvvccvvcv")
        return if(data.isNotEmpty()) Json.decodeFromString(DataUser.serializer(), data) else null
    }

    override suspend fun deleteUser() {
        jsonStorage.putItemsToStorage(USER_JSON_FILED, "")
    }
}