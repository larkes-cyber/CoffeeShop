package com.example.coffeeshop.domain.mapper

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.network.model.CoffeeDto
import com.example.coffeeshop.domain.model.Coffee

fun Coffee.toDataCoffee():DataCoffee{
    return DataCoffee(
        id = id,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price
    )
}

fun DataCoffee.toCoffee():Coffee{
    return Coffee(
        id = id,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price
    )
}

fun DataCoffee.toCoffeeDto(session:String):CoffeeDto{
    return CoffeeDto(
        id = id,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price,
        session = session
    )
}

fun CoffeeDto.toDataCoffee():DataCoffee{
    return DataCoffee(
        id = id!!,
        photoSrc = photoSrc,
        includeBeans = includeBeans,
        includeMilk = includeMilk,
        categoryId = categoryId,
        categoryTitle = categoryTitle,
        subtitle = subtitle,
        description = description,
        totalScore = totalScore,
        scoreCount = scoreCount,
        price = price
    )
}