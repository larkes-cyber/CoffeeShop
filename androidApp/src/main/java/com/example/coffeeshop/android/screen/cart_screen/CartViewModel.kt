package com.example.coffeeshop.android.screen.cart_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.android.untils.Constants.DELIVER_TAB_TITLE
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor():ViewModel() {

    private val _cartUIState = MutableStateFlow(CartUIState())
    val cartUIState:StateFlow<CartUIState> = _cartUIState

    private val _addressUIState = MutableStateFlow(AddressUIState())
    val addressUIState:StateFlow<AddressUIState> = _addressUIState

    private val _receiveOrderModeUIState = MutableStateFlow(DELIVER_TAB_TITLE)
    val receiveOrderModeUIState:StateFlow<String> = _receiveOrderModeUIState

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

    private suspend fun getAddress(points:Pair<Float, Float>):String{
        _addressUIState.value = addressUIState.value.copy(isLoading = true)
        return UseCases.useGetAddress().execute(points).data ?: ""
    }

    private fun checkAmount(){
        var sum = 0f
        cartUIState.value.coffee.forEach {item ->
            sum += item.first.price * item.second.toFloat()
        }
        _cartUIState.value = cartUIState.value.copy(totalPrice = sum)
    }

    fun closeMap(){
        _addressUIState.value = AddressUIState()
    }
    fun changeMapActive(active:Boolean){
        _addressUIState.value = addressUIState.value.copy(showingMap = active)
    }
    fun onAddressChange(points:Pair<Float, Float>){
        viewModelScope.launch {
            val address = getAddress(points)
            _addressUIState.value = addressUIState.value.copy(selectedAddress = address, isLoading = false)
        }
    }

    fun onReceiveModeChange(title:String){
        _receiveOrderModeUIState.value = title
    }

}