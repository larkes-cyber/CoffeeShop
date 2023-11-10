package com.example.coffeeshop.data.source.order

import com.example.coffeeshop.data.network.model.OrderDto
import com.example.coffeeshop.data.network.model.PaymentDto
import com.example.coffeeshop.data.network.model.SessionDto
import com.example.coffeeshop.data.source.order.OrderRemoteDataSource.Companion.POST_ADD_USER_ORDER
import com.example.coffeeshop.data.source.order.OrderRemoteDataSource.Companion.POST_GET_USER_ORDERS
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class OrderRemoteDataSourceImpl(
    private val httpClient: HttpClient
):OrderRemoteDataSource {
    @OptIn(InternalAPI::class)
    override suspend fun sendPayment(paymentDto: PaymentDto) {
        httpClient.post(POST_ADD_USER_ORDER){
            contentType(ContentType.Application.Json)
            body = paymentDto
        }
    }

    @OptIn(InternalAPI::class)
    override suspend fun getOrders(session: String): List<OrderDto> {
        val response:HttpResponse = httpClient.post(POST_GET_USER_ORDERS){
            contentType(ContentType.Application.Json)
            body = SessionDto(session)
        }
        return Json.decodeFromString(response.bodyAsText())
    }
}