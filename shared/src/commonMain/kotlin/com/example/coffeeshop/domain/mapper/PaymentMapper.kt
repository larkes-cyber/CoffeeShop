package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataPayment
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.domain.model.Payment

fun Payment.toDataPayment(session:String):DataPayment{
    return DataPayment(
        session = session,
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