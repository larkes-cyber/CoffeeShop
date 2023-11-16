package com.example.coffeeshop.android.screen.favorite_coffee_screen

import android.graphics.BitmapFactory
import android.util.Log
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

    private val _searchCoffeeUIState = MutableStateFlow(SearchCoffeeUIState())
    val searchCoffeeUIState:StateFlow<SearchCoffeeUIState> = _searchCoffeeUIState

    fun getFavoriteCoffee(){
        viewModelScope.launch {
            _favoriteCoffeeUIState.value = FavoriteCoffeeUIState(isLoading = true)
            val coffee = UseCases.useGetFavoriteCoffee().execute().data ?: listOf()
            _favoriteCoffeeUIState.value = FavoriteCoffeeUIState(coffee = coffee, savedCoffee = coffee)
        }
    }

    fun onSearchText(symbols:String){

        _searchCoffeeUIState.value = SearchCoffeeUIState(searchMode = symbols.isNotEmpty(), symbols = symbols)

        val filteredCoffee = favoriteCoffeeUIState.value.savedCoffee.filter {

            it.categoryTitle.contains(symbols, ignoreCase = true) || it.subtitle.contains(symbols, ignoreCase = true)
        }

        _favoriteCoffeeUIState.value = favoriteCoffeeUIState.value.copy(coffee = filteredCoffee)

        if(favoriteCoffeeUIState.value.savedCoffee.isEmpty()){
            _favoriteCoffeeUIState.value = favoriteCoffeeUIState.value.copy(coffee = filteredCoffee)
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