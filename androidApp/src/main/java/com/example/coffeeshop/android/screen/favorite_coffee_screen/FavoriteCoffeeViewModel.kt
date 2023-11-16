package com.example.coffeeshop.android.screen.favorite_coffee_screen

import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteCoffeeViewModel @Inject constructor():ViewModel() {

    private val _favoriteCoffeeUIState = MutableStateFlow(FavoriteCoffeeUIState())
    val favoriteCoffeeUIState:StateFlow<FavoriteCoffeeUIState> = _favoriteCoffeeUIState

    init {
        viewModelScope.launch {
            _favoriteCoffeeUIState.value = FavoriteCoffeeUIState(isLoading = true)
            val coffee = UseCases.useGetFavoriteCoffee().execute().data!!
            _favoriteCoffeeUIState.value = FavoriteCoffeeUIState(coffee = coffee)
        }
    }

    fun getCoffeeImage(id:String, imageState: MutableState<ImageBitmap?>){
        viewModelScope.launch {
            val bytes = UseCases.useGetCoffeeImage().execute(id).data ?: return@launch
            val bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.size)
            imageState.value = bmp.asImageBitmap()
        }
    }

}