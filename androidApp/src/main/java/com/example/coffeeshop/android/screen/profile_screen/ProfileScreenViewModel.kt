package com.example.coffeeshop.android.screen.profile_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import com.example.coffeeshop.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor():ViewModel() {

    private val _userUIState = MutableStateFlow<User?>(null)
    val userUIState:StateFlow<User?> = _userUIState

    val languages = listOf("Russian", "English")

    private val _selectedLangUIState = MutableStateFlow("English")
    val selectedLangUIState:StateFlow<String> = _selectedLangUIState

    private val _showingPickerUIState = MutableStateFlow(false)
    val showingPickerUIState:StateFlow<Boolean> = _showingPickerUIState

    init {
        fetchUser()
    }
    private fun fetchUser(){
        viewModelScope.launch {
            val user = UseCases.useGetUserData().execute().data
            _userUIState.value = user
        }
    }

    fun onTitleChange(title:String){
        _selectedLangUIState.value = title
        switchShowingPicker()
    }

    fun switchShowingPicker(){
        _showingPickerUIState.value = !showingPickerUIState.value
    }


}