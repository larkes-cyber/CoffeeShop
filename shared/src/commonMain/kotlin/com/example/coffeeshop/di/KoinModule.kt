package com.example.coffeeshop.di

import com.example.coffeeshop.data.json_storage.JsonStorage
import com.example.coffeeshop.data.json_storage.JsonStorageImpl
import com.example.coffeeshop.data.repository.CartRepositoryImpl
import com.example.coffeeshop.data.repository.CoffeeRepositoryImpl
import com.example.coffeeshop.data.repository.OrderRepositoryImpl
import com.example.coffeeshop.data.repository.UserRepositoryImpl
import com.example.coffeeshop.data.source.cart.CartDiskDataSource
import com.example.coffeeshop.data.source.cart.CartDiskDataSourceImpl
import com.example.coffeeshop.data.source.coffee.CoffeeDiskDataSource
import com.example.coffeeshop.data.source.coffee.CoffeeDiskDataSourceImpl
import com.example.coffeeshop.data.source.coffee.CoffeeRemoteDataSource
import com.example.coffeeshop.data.source.coffee.CoffeeRemoteDataSourceImpl
import com.example.coffeeshop.data.source.order.OrderDiskDataSource
import com.example.coffeeshop.data.source.order.OrderDiskDataSourceImpl
import com.example.coffeeshop.data.source.order.OrderRemoteDataSource
import com.example.coffeeshop.data.source.order.OrderRemoteDataSourceImpl
import com.example.coffeeshop.data.source.user.UserDiskDataSource
import com.example.coffeeshop.data.source.user.UserDiskDataSourceImpl
import com.example.coffeeshop.data.source.user.UserRemoteDataSource
import com.example.coffeeshop.data.source.user.UserRemoteDataSourceImpl
import com.example.coffeeshop.database.CartDatabase
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.domain.usecase.UseAddCart
import com.example.coffeeshop.domain.usecase.UseAddFavoriteCoffee
import com.example.coffeeshop.domain.usecase.UseAuthUser
import com.example.coffeeshop.domain.usecase.UseEditUser
import com.example.coffeeshop.domain.usecase.UseGetCarts
import com.example.coffeeshop.domain.usecase.UseGetCoffeeByCategory
import com.example.coffeeshop.domain.usecase.UseGetCoffeeCategories
import com.example.coffeeshop.domain.usecase.UseGetCoffeeDetailById
import com.example.coffeeshop.domain.usecase.UseGetCoffeeImage
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
import com.russhwolf.settings.Settings
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
            single { Settings() }
            single { httpClient }
            single { database }
            single<JsonStorage> { JsonStorageImpl(get()) }
        },
        dataSourceModule(),
        repositoryModule(),
        domainModule()
    )

}

object UseCases:KoinComponent{
    fun useAddCart() = get<UseAddCart>()
    fun useAddFavoriteCoffee() = get<UseAddFavoriteCoffee>()
    fun useAuthUser() = get<UseAuthUser>()
    fun useGetCarts() = get<UseGetCarts>()
    fun useGetCoffeeByCategory() = get<UseGetCoffeeByCategory>()
    fun useGetCoffeeCategories() = get<UseGetCoffeeCategories>()
    fun useGetCoffeeDetailById() = get<UseGetCoffeeDetailById>()
    fun useGetFavoriteCoffee() = get<UseGetFavoriteCoffee>()
    fun useGetOrders() = get<UseGetOrders>()
    fun useMakePayment() = get<UseMakePayment>()
    fun useRegisterUser() = get<UseRegisterUser>()
    fun useRemoveFavoriteCoffee() = get<UseRemoveFavoriteCoffee>()
    fun useSearchForCoffee() = get<UseSearchForCoffee>()
    fun useSyncCoffee() = get<UseSyncCoffee>()
    fun useSyncCoffeeCategories() = get<UseSyncCoffeeCategories>()
    fun useSyncOrders() = get<UseSyncOrders>()
    fun useSyncUserData() = get<UseSyncUserData>()
    fun useUploadUserPhoto() = get<UseUploadUserPhoto>()
    fun useGetUserData() = get<UseGetUserData>()
    fun useGetCoffeeImage() = get<UseGetCoffeeImage>()

}