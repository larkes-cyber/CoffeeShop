package com.example.coffeeshop.di

import com.example.coffeeshop.domain.usecase.UseAddCart
import com.example.coffeeshop.domain.usecase.UseAddFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseAuthUser
import com.example.coffeeshop.domain.usecase.UseEditUser
import com.example.coffeeshop.domain.usecase.UseGetCarts
import com.example.coffeeshop.domain.usecase.UseGetCoffeeByCategory
import com.example.coffeeshop.domain.usecase.UseGetCoffeeCategories
import com.example.coffeeshop.domain.usecase.UseGetCoffeeDetailById
import com.example.coffeeshop.domain.usecase.UseGetFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseGetOrders
import com.example.coffeeshop.domain.usecase.UseGetUserData
import com.example.coffeeshop.domain.usecase.UseMakePayment
import com.example.coffeeshop.domain.usecase.UseRegisterUser
import com.example.coffeeshop.domain.usecase.UseRemoveFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseSearchForCoffee
import com.example.coffeeshop.domain.usecase.UseSyncCoffee
import com.example.coffeeshop.domain.usecase.UseSyncCoffeeCategories
import com.example.coffeeshop.domain.usecase.UseSyncOrders
import com.example.coffeeshop.domain.usecase.UseSyncUserData
import com.example.coffeeshop.domain.usecase.UseUploadUserPhoto
import org.koin.dsl.module

fun domainModule() = module {
    factory { UseAddCart(get()) }
    factory { UseAddFavoriteCoffee(get()) }
    factory { UseAuthUser(get()) }
    factory { UseEditUser(get()) }
    factory { UseGetCarts(get()) }
    factory { UseGetCoffeeByCategory(get()) }
    factory { UseGetCoffeeCategories(get()) }
    factory { UseGetCoffeeDetailById(get()) }
    factory { UseGetFavoriteCoffee(get(), get()) }
    factory { UseGetOrders(get()) }
    factory { UseGetUserData(get()) }
    factory { UseMakePayment(get()) }
    factory { UseRegisterUser(get()) }
    factory { UseRemoveFavoriteCoffee(get()) }
    factory { UseSearchForCoffee(get()) }
    factory { UseSyncCoffee(get()) }
    factory { UseSyncCoffeeCategories(get()) }
    factory { UseSyncOrders(get()) }
    factory { UseSyncUserData(get()) }
    factory { UseUploadUserPhoto(get()) }
}