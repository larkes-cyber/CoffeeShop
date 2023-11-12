package com.example.coffeeshop.di

import com.example.coffeeshop.data.repository.CartRepositoryImpl
import com.example.coffeeshop.data.repository.CoffeeRepositoryImpl
import com.example.coffeeshop.data.repository.OrderRepositoryImpl
import com.example.coffeeshop.data.repository.UserRepositoryImpl
import com.example.coffeeshop.domain.repository.CartRepository
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.domain.repository.OrderRepository
import com.example.coffeeshop.domain.repository.UserRepository
import org.koin.dsl.module

fun repositoryModule() = module {

    single<CartRepository> { CartRepositoryImpl(get()) }
    single<CoffeeRepository> { CoffeeRepositoryImpl(get(), get(), get()) }
    single<OrderRepository> { OrderRepositoryImpl(get(), get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

}