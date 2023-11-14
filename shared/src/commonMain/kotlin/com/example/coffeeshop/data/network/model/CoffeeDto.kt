package com.example.coffeeshop.data.network.model

import kotlinx.serialization.Serializable

@Serializable()
data class CoffeeDto(
    val session:String,
    val id:String? = null,
    val includeBeans:Boolean = false,
    val includeMilk:Boolean = false,
    val categoryId:String,
    val categoryTitle:String,
    val subtitle:String,
    val description:String,
    val totalScore:Float = 0.0f,
    val scoreCount:Int = 0,
    val price:Float
)