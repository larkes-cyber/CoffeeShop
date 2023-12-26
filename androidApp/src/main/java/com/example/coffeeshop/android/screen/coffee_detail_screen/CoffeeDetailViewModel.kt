package com.example.coffeeshop.android.screen.coffee_detail_screen

import androidx.lifecycle.SavedStateHandle
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
class CoffeeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    private val _coffeeDetailUIState = MutableStateFlow(CoffeeDetailUIState())
    val coffeeDetailUIState:StateFlow<CoffeeDetailUIState> = _coffeeDetailUIState

    private val _hasBeenAddedToCartUIState = MutableStateFlow(false)
    val hasBeenAddedToCartUIState:StateFlow<Boolean> = _hasBeenAddedToCartUIState

    fun getCoffee(){
        viewModelScope.launch {
            _coffeeDetailUIState.value = CoffeeDetailUIState(isLoading = true)
            val id = savedStateHandle.get<String>("id")
            val coffee = UseCases.useGetCoffeeDetailById().execute(id!!).data
            val checkFavoriteCoffee = UseCases.useCheckFavoriteCoffee().execute(id)
            _coffeeDetailUIState.value = coffeeDetailUIState.value.copy(coffee = coffee, isLoading = false, coffeeIsFavorite = checkFavoriteCoffee)
        }
    }


    fun switchCoffeeSize(index:Int){
        _coffeeDetailUIState.value = coffeeDetailUIState.value.copy(selectedCoffeeSize = index)
    }

    fun switchCoffeeFavorite(){
        viewModelScope.launch {
            _coffeeDetailUIState.value = coffeeDetailUIState.value.copy(coffeeIsFavorite = !coffeeDetailUIState.value.coffeeIsFavorite)
            if(!coffeeDetailUIState.value.coffeeIsFavorite){
                UseCases.useRemoveFavoriteCoffee().execute(coffeeDetailUIState.value.coffee!!.id)
            }else{
                UseCases.useAddFavoriteCoffee().execute(coffeeDetailUIState.value.coffee!!.id)
            }
        }
    }

    fun changeCoffeeCartStatus(){
        viewModelScope.launch {
            _hasBeenAddedToCartUIState.value = hasBeenAddedToCartUIState.value.not()
            if(hasBeenAddedToCartUIState.value){
                UseCases.useAddCart().execute(CartItem(amount = 1, productId = coffeeDetailUIState.value.coffee?.id ?: ""))
                return@launch
            }
            UseCases.useDeleteCart().execute(coffeeDetailUIState.value.coffee?.id ?: "")
        }
    }


}