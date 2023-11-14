package com.example.coffeeshop.android.screen.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor():ViewModel(){

    private val _mainScreenUIState = MutableStateFlow(MainScreenUIState())
    val mainScreenUIState:StateFlow<MainScreenUIState> = _mainScreenUIState


    init {
        syncCoffee()
    }

    fun syncCoffee(){
        viewModelScope.launch {
           loadCategories().join()
            loadCoffee().join()
        }
    }
    private fun loadCategories(): Job {
        return viewModelScope.launch {
            _mainScreenUIState.value = mainScreenUIState.value.copy(isCategoriesLoading = true)
            val categories = UseCases.useGetCoffeeCategories().execute().data!!
            _mainScreenUIState.value = mainScreenUIState.value.copy(
                categories = categories,
                isCategoriesLoading = false,
                selectedCategory = categories[0].id
            )
        }
    }
    private suspend fun loadCoffee(): Job{
        return viewModelScope.launch {
            _mainScreenUIState.value = mainScreenUIState.value.copy(isCoffeeLoading = true)
            val coffee = UseCases.useGetCoffeeByCategory().execute(mainScreenUIState.value.selectedCategory).data!!
            _mainScreenUIState.value = mainScreenUIState.value.copy(
                coffee = coffee,
                isCoffeeLoading = false
            )
        }
    }
}