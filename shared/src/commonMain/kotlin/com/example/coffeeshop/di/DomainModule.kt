package com.example.coffeeshop.di

import com.example.coffeeshop.domain.usecase.UseAddCart
import com.example.coffeeshop.domain.usecase.UseAddFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseAuthUser
import com.example.coffeeshop.domain.usecase.UseCheckFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseEditUser
import com.example.coffeeshop.domain.usecase.UseGetCarts
import com.example.coffeeshop.domain.usecase.UseGetCoffeeByCategory
import com.example.coffeeshop.domain.usecase.UseGetCoffeeCategories
import com.example.coffeeshop.domain.usecase.UseGetCoffeeDetailById
import com.example.coffeeshop.domain.usecase.UseGetCoffeeImage
import com.example.coffeeshop.domain.usecase.UseGetFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseGetOrders
import com.example.coffeeshop.domain.usecase.UseGetUserData
import com.example.coffeeshop.domain.usecase.UseChangeCartAmount
import com.example.coffeeshop.domain.usecase.UseDeleteCart
import com.example.coffeeshop.domain.usecase.UseDeleteUserData
import com.example.coffeeshop.domain.usecase.UseGetCoffeeCartAmount
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
    single { UseAddCart(get(), get()) }
    single { UseAddFavoriteCoffee(get()) }
    single { UseAuthUser(get()) }
    single { UseEditUser(get()) }
    single { UseGetCarts(get()) }
    single { UseGetCoffeeByCategory(get(), get()) }
    single { UseGetCoffeeCategories(get()) }
    single { UseGetCoffeeDetailById(get(), get()) }
    single { UseGetFavoriteCoffee(get(), get(), get()) }
    single { UseGetOrders(get()) }
    single { UseMakePayment(get()) }
    single { UseRegisterUser(get()) }
    single { UseRemoveFavoriteCoffee(get()) }
    single { UseSearchForCoffee(get(), get()) }
    single { UseSyncCoffee(get()) }
    single { UseSyncCoffeeCategories(get()) }
    single{ UseSyncOrders(get()) }
    single { UseSyncUserData(get()) }
    single { UseUploadUserPhoto(get()) }
    single { UseGetOrders(get()) }
    single { UseGetUserData(get()) }
    single { UseGetUserData(get()) }
    single { UseGetCoffeeImage(get()) }
    single { UseCheckFavoriteCoffee(get()) }
    single { UseChangeCartAmount(get()) }
    single { UseGetCoffeeCartAmount(get()) }
    single { UseDeleteCart(get()) }
    single { UseDeleteUserData(get(), get()) }

}