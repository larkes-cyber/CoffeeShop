package com.example.coffeeshop.data.model

data class DataCoffee(
    val id:String,
    val photoSrc:String?,
    val includeBeans:Boolean,
    val includeMilk:Boolean,
    val categoryId:String,
    val categoryTitle:String,
    val subtitle:String,
    val description:String,
    val totalScore:Float,
    val scoreCount:Int,
    val price:Float
)