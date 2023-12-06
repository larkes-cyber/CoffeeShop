package com.example.coffeeshop.android.screen.cart_screen

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor():ViewModel() {

    private val _cartUIState = MutableStateFlow(CartUIState())
    val cartUIState:StateFlow<CartUIState> = _cartUIState

    private val _showMapUIState = MutableStateFlow(false)
    val showMapUIState:StateFlow<Boolean> = _showMapUIState

    private val _selectedAddressUIState = MutableStateFlow("")
    val selectedAddressUIState:StateFlow<String> = _selectedAddressUIState

    fun getCartCoffee(){
        viewModelScope.launch {
            _cartUIState.value = cartUIState.value.copy(isLoading = true)

            val res = UseCases.useGetCarts().execute()
            _cartUIState.value = cartUIState.value.copy(isLoading = false)
            if(res.data != null) {
                res.data!!.forEach {item ->
                    val coffee = UseCases.useGetCoffeeDetailById().execute(item.productId).data!!
                    val coffeeList = cartUIState.value.coffee.toMutableList()
                    coffeeList.add(Pair(coffee, item.amount))
                    _cartUIState.value = CartUIState(coffee = coffeeList)
                    checkAmount()
                }
            }
        }
    }

    fun getCoffeeImage(id:String, imageState: MutableState<ImageBitmap?>){
        viewModelScope.launch {
            val bytes = UseCases.useGetCoffeeImage().execute(id).data ?: return@launch
            val bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.size)
            imageState.value = bmp.asImageBitmap()
        }
    }

    fun changeAmount(index:Int, amount:Int){
        viewModelScope.launch {
            val coffeeList = cartUIState.value.coffee.toMutableList()
            val item = coffeeList[index]
            if(item.second + amount == 0){
                coffeeList.remove(item)
            }else{
                coffeeList[index] = Pair(item.first, item.second + amount)
            }
            _cartUIState.value = cartUIState.value.copy(coffee = coffeeList)
            checkAmount()
            UseCases.useChangeCartAmount().execute(item.first.id, amount)

        }
    }

    fun getAddress(points:Pair<Float, Float>){
        CoroutineScope(Dispatchers.IO).launch {
            val address = UseCases.useGetAddress().execute(points)
            if(address.data != null) _selectedAddressUIState.value = address.data!!
        }
    }

    private fun checkAmount(){
        var sum = 0f
        cartUIState.value.coffee.forEach {item ->
            sum += item.first.price * item.second.toFloat()
        }
        _cartUIState.value = cartUIState.value.copy(totalPrice = sum)
    }

    fun switchShowingMap(){
        _showMapUIState.value = showMapUIState.value.not()
    }


}