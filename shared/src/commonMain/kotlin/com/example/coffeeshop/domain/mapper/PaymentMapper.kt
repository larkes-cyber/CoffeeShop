package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataPayment
import com.example.coffeeshop.data.network.model.PaymentDto
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.Payment

fun Payment.toDataPayment():DataPayment{
    return DataPayment(
        products = products.map { it.id },
        price = price
    )
}

fun DataPayment.toPayment(
    session: String,
    products:List<Coffee>
):Payment{
    return Payment(
        products = products,
        price = price
    )
}

