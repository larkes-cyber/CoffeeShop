package com.example.coffeeshop.android.screen.favorite_coffee_screen

import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import com.example.coffeeshop.domain.model.CartItem
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

    fun onAddCartChange(id:String, addCart:Boolean){
        viewModelScope.launch {
            UseCases.useAddCart().execute(CartItem(
                productId = id,
                amount = if(addCart) 1 else -1
            ))
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