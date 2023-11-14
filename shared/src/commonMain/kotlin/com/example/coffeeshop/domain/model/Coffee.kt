package com.example.coffeeshop.domain.model

data class Coffee(
    val id:String,
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