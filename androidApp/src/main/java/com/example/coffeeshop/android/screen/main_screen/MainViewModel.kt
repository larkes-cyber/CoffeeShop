package com.example.coffeeshop.android.screen.main_screen

import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import com.example.coffeeshop.domain.model.CartItem
import com.example.coffeeshop.domain.model.Coffee
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

    private val _searchCoffeeUIState = MutableStateFlow(SearchCoffeeUIState())
    val searchCoffeeUIState:StateFlow<SearchCoffeeUIState> = _searchCoffeeUIState

    private val _userUIState = MutableStateFlow(UserUIState())
    val userUIState:StateFlow<UserUIState> = _userUIState

    private val _hasBeenExit = MutableStateFlow(false)
    val hasBeenExit:StateFlow<Boolean> = _hasBeenExit

    fun loadUserData(){
        viewModelScope.launch {
            _userUIState.value = UserUIState(isLoading = true)
            _userUIState.value = UserUIState(user = UseCases.useGetUserData().execute().data)
        }
    }

    fun deleteUser(){
        viewModelScope.launch {
            UseCases.useDeleteUser().execute()
            _hasBeenExit.value = true
        }
    }
    fun syncCoffee(){
        viewModelScope.launch {
            _mainScreenUIState.value = mainScreenUIState.value.copy(isCoffeeLoading = true, categories = listOf())
            _mainScreenUIState.value = mainScreenUIState.value.copy(isCategoriesLoading = true, coffee = listOf())
            UseCases.useSyncCoffee().execute()
            UseCases.useSyncCoffeeCategories().execute()
            loadCategories().join()
            loadCoffee().join()
        }
    }
    fun loadCategories(): Job {
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
    fun loadCoffee(): Job{
        return viewModelScope.launch {
            _mainScreenUIState.value = mainScreenUIState.value.copy(isCoffeeLoading = true)
            val coffee = UseCases.useGetCoffeeByCategory().execute(mainScreenUIState.value.selectedCategory).data!!
            _mainScreenUIState.value = mainScreenUIState.value.copy(
                coffee = coffee,
                isCoffeeLoading = false
            )
        }
    }

    fun changeCategory(id:String){
        viewModelScope.launch {
            _mainScreenUIState.value = mainScreenUIState.value.copy(selectedCategory = id, isCoffeeLoading = true, coffee = listOf())
            val coffee = UseCases.useGetCoffeeByCategory().execute(id).data!!
            _mainScreenUIState.value = mainScreenUIState.value.copy(selectedCategory = id, isCoffeeLoading = false, coffee = coffee)
        }
    }

    fun getCoffeeImage(id:String, imageState:MutableState<ImageBitmap?>){
        viewModelScope.launch {
            val bytes = UseCases.useGetCoffeeImage().execute(id).data ?: return@launch
            val bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.size)
            imageState.value = bmp.asImageBitmap()
        }
    }

    fun searchForCoffee(symbols:String){
        viewModelScope.launch {
            _searchCoffeeUIState.value = SearchCoffeeUIState(symbols = symbols, searchMode = symbols.isNotEmpty())
            val coffee = UseCases.useSearchForCoffee().execute(symbols).data!!
            _mainScreenUIState.value = mainScreenUIState.value.copy(coffee = coffee)
            if(symbols.isEmpty()){
                loadCoffee().join()
            }
        }
    }

    fun addToCart(id:String){
        viewModelScope.launch {
            UseCases.useAddCart().execute(CartItem(
                productId = id,
                amount = 1
            ))
        }
    }


    fun prepareCoffeeData(list:List<Coffee>):List<List<Coffee>>{

        val pairList = mutableListOf<List<Coffee>>()

        var i = 0
        while (i < list.size) {
            val pair = ArrayList<Coffee>()

            pair.add(list[i])

            // Проверяем, есть ли следующий элемент в списке
            if (i + 1 < list.size) {
                pair.add(list[i + 1])
            }
            pairList.add(pair)
            i += 2
        }

        return pairList
    }
}