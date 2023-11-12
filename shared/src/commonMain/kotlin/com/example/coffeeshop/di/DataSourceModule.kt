package com.example.coffeeshop.di

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
import org.koin.dsl.module

fun dataSourceModule() = module {

    single<CartDiskDataSource> { CartDiskDataSourceImpl(get()) }
    single<CoffeeDiskDataSource> { CoffeeDiskDataSourceImpl(get()) }
    single<CoffeeRemoteDataSource> { CoffeeRemoteDataSourceImpl(get()) }
    single<OrderDiskDataSource> { OrderDiskDataSourceImpl(get()) }
    single<OrderRemoteDataSource> { OrderRemoteDataSourceImpl(get()) }

}