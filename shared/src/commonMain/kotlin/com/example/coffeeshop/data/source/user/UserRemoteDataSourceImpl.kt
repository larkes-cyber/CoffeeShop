package com.example.coffeeshop.data.source.user

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataUser
import com.example.coffeeshop.data.network.model.CategoryListDto
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.data.network.model.CoffeeListDto
import com.example.coffeeshop.data.network.model.FavoriteCoffeeDto
import com.example.coffeeshop.data.network.model.LocationDto
import com.example.coffeeshop.data.network.model.LoginDto
import com.example.coffeeshop.data.network.model.SessionDto
import com.example.coffeeshop.data.network.model.UserDto
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_ADD_FAVORITE_COFFEE
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_AUTH_USER
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_EDIT_USER
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_GET_USER_INFO
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_REGISTER_USER
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_REMOVE_USER_INFO
import com.example.coffeeshop.data.source.user.UserRemoteDataSource.Companion.POST_UPLOAD_PHOTO
import com.example.coffeeshop.logInTerminal
import com.example.coffeeshop.untils.Constants.YANDEX_GEOCODE_API
import com.example.coffeeshop.untils.Constants.YANDEX_GEOCODE_API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.Json.Default.decodeFromString


class UserRemoteDataSourceImpl(
    private val httpClient: HttpClient
):UserRemoteDataSource {
    override suspend fun registerUser(userDto: UserDto): UserDto {
        val response:HttpResponse = httpClient.post(POST_REGISTER_USER){
            contentType(ContentType.Application.Json)
            setBody(userDto)
        }
        if(response.status == HttpStatusCode.OK){
            return Json.decodeFromString(response.bodyAsText())
        }
        throw Exception(response.bodyAsText())
    }


    override suspend fun authUser(loginDto: LoginDto): UserDto {
        val response:HttpResponse = httpClient.post(POST_AUTH_USER){
            contentType(ContentType.Application.Json)
            setBody(loginDto)
        }
        logInTerminal(response.bodyAsText())
        return Json.decodeFromString(response.bodyAsText())
    }

    override suspend fun editUser(userDto: UserDto) {
        httpClient.post(POST_EDIT_USER){
            contentType(ContentType.Application.Json)
            setBody(userDto)
        }
    }

    override suspend fun addFavoriteCoffee(coffeeDto: FavoriteCoffeeDto) {
        httpClient.post(POST_ADD_FAVORITE_COFFEE){
            contentType(ContentType.Application.Json)
            setBody(coffeeDto)
        }
    }


    override suspend fun getUserInfo(session: String): UserDto {
        val response:HttpResponse = httpClient.post(POST_GET_USER_INFO){
            contentType(ContentType.Application.Json)
            setBody(SessionDto(session))
        }
        logInTerminal(response.bodyAsText())
        return Json.decodeFromString(response.bodyAsText())
    }

    override suspend fun removeFavoriteCoffee(favoriteCoffeeDto: FavoriteCoffeeDto) {
        httpClient.post(POST_REMOVE_USER_INFO){
            contentType(ContentType.Application.Json)
            setBody(favoriteCoffeeDto)
        }
    }

    override suspend fun uploadProfileImage(userId: String, file: ByteArray) {
        httpClient.post(POST_UPLOAD_PHOTO){

            url{
                parameters.append("login",userId)
            }

            setBody(
                MultiPartFormDataContent(
                formData {
                    append("description", "user_image")
                    append("image", file, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"ktor_logo.png\"")
                    })
                },
                boundary = "WebAppBoundary"
                 )
            )
        }
    }

    override suspend fun getAddress(points: Pair<Float, Float>): String? {
//        println("dfdffdfffsdfsdfsdf dddddddddd")
//        logInTerminal("started")
        val response = httpClient.get(YANDEX_GEOCODE_API){
            url {
                parameters.append("apikey", YANDEX_GEOCODE_API_KEY)
                parameters.append("geocode", "${points.first},${points.second}")
                parameters.append("format","json")
                parameters.append("result", "1")
            }
        }

        val location = Json.decodeFromString<LocationDto>(response.bodyAsText())
//
        val address =  location.response?.geoObjectCollection?.featureMember?.get(0)?.geoObject?.metaDataProperty?.geocoderMetaData?.text

        return address
    }
}