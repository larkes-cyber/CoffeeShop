package com.example.coffeeshop.data.repository

import com.example.coffeeshop.data.model.DataCoffee
import com.example.coffeeshop.data.model.DataCoffeeCategory
import com.example.coffeeshop.data.source.coffee.CoffeeDiskDataSource
import com.example.coffeeshop.data.source.coffee.CoffeeRemoteDataSource
import com.example.coffeeshop.domain.mapper.toDataCoffee
import com.example.coffeeshop.domain.mapper.toDataCoffeeCategory
import com.example.coffeeshop.domain.repository.CoffeeRepository
import com.example.coffeeshop.domain.repository.UserRepository
import com.example.coffeeshop.logInTerminal

class CoffeeRepositoryImpl(
    private val coffeeDiskDataSource: CoffeeDiskDataSource,
    private val coffeeRemoteDataSource: CoffeeRemoteDataSource,
    private val userRepository: UserRepository
):CoffeeRepository {


    override suspend fun syncCoffee() {
        val session = userRepository.getUser()!!.session
        val coffee = coffeeRemoteDataSource.getAllCoffee(session!!)
        coffeeDiskDataSource.cleanUpCoffee()
        coffee.forEach {
            coffeeDiskDataSource.insertCoffee(it.toDataCoffee())
        }
    }

    override suspend fun syncCoffeeCategory() {
        logInTerminal(userRepository.getUser().toString())
        val session = userRepository.getUser()!!.session
        val coffee = coffeeRemoteDataSource.getAllCoffeeCategory(session!!)
        coffeeDiskDataSource.cleanUpCoffeeCategory()
        coffee.forEach {
            coffeeDiskDataSource.insertCoffeeCategory(it.toDataCoffeeCategory())
        }
    }

    override suspend fun getCoffeeByCategory(id: String):List<DataCoffee>{
        return coffeeDiskDataSource.getCoffee().filter {
            it.categoryId == id
        }
    }

    override suspend fun getCoffeeDetail(id: String):DataCoffee {
        return coffeeDiskDataSource.getCoffee().find { it.id == id }!!
    }

    override suspend fun getCoffeeCategories(): List<DataCoffeeCategory> {
        return coffeeDiskDataSource.getCoffeeCategories()
    }

    override suspend fun searchForCoffee(symbols:String): List<DataCoffee> {
        return coffeeDiskDataSource.getCoffee().filter {
            it.categoryTitle.contains(symbols, ignoreCase = true)
                    ||  it.subtitle.contains(symbols, ignoreCase = true)
                    ||  it.description.contains(symbols, ignoreCase = true)
        }
    }
}