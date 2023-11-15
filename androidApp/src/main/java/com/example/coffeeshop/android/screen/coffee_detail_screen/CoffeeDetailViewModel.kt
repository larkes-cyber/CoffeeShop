package com.example.coffeeshop.android.screen.coffee_detail_screen

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _coffeeDetailUIState = MutableStateFlow(CoffeeDetailUIState())
    val coffeeDetailUIState:StateFlow<CoffeeDetailUIState> = _coffeeDetailUIState

    init {
        getCoffee()
        pullCoffeeImage()
    }
    private fun getCoffee(){
        viewModelScope.launch {
            _coffeeDetailUIState.value = CoffeeDetailUIState(isLoading = true)
            val id = savedStateHandle.get<String>("id")
            val coffee = UseCases.useGetCoffeeDetailById().execute(id!!).data
            _coffeeDetailUIState.value = coffeeDetailUIState.value.copy(coffee = coffee, isLoading = false)
        }
    }

    private fun pullCoffeeImage(){
        viewModelScope.launch {
            val id = savedStateHandle.get<String>("id")!!
            val image = UseCases.useGetCoffeeImage().execute(id).data!!
            val bmp = BitmapFactory.decodeByteArray(image, 0, image.size).asImageBitmap()
            _coffeeDetailUIState.value = coffeeDetailUIState.value.copy(image = bmp)
        }
    }

    fun switchCoffeeSize(index:Int){
        _coffeeDetailUIState.value = coffeeDetailUIState.value.copy(selectedCoffeeSize = index)
    }


}