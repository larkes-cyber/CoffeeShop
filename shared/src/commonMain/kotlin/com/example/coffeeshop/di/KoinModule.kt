package com.example.coffeeshop.di

import com.example.coffeeshop.database.CartDatabase
import com.example.coffeeshop.domain.usecase.UseAddCart
import com.example.coffeeshop.domain.usecase.UseAddFavoriteCoffee
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
import io.ktor.client.HttpClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(
    database: CartDatabase,
    httpClient: HttpClient
) = startKoin {

    modules(
        module {
            single { httpClient }
            single { database }
            dataSourceModule()
            repositoryModule()
            domainModule()
        }
    )

}

object UseCases:KoinComponent{
    fun useAddCart() = get<UseAddCart>()
    fun useAddFavoriteCoffee() = get<UseAddFavoriteCoffee>()
    fun useAuthUser() = get<UseEditUser>()
    fun useGetCarts() = get<UseGetCarts>()
    fun useGetCoffeeByCategory() = get<UseGetCoffeeByCategory>()
    fun useGetCoffeeCategories() = get<UseGetCoffeeCategories>()
    fun useGetCoffeeDetailById() = get<UseGetCoffeeDetailById>()
    fun useGetFavoriteCoffee() = get<UseGetFavoriteCoffee>()
    fun useGetOrders() = get<UseGetOrders>()
    fun useGetUserData() = get<UseGetUserData>()
    fun useMakePayment() = get<UseMakePayment>()
    fun useRegisterUser() = get<UseRegisterUser>()
    fun useRemoveFavoriteCoffee() = get<UseRemoveFavoriteCoffee>()
    fun useSearchForCoffee() = get<UseSearchForCoffee>()
    fun useSyncCoffee() = get<UseSyncCoffee>()
    fun useSyncCoffeeCategories() = get<UseSyncCoffeeCategories>()
    fun useSyncOrders() = get<UseSyncOrders>()
    fun useSyncUserData() = get<UseSyncUserData>()
    fun useUploadUserPhoto() = get<UseUploadUserPhoto>()

}