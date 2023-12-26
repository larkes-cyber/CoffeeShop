package com.example.coffeeshop.android.screen.profile_screen

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.di.UseCases
import com.example.coffeeshop.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor():ViewModel() {

    val languages = listOf("Russian", "English")

    private val _userUIState = MutableStateFlow<User?>(null)
    val userUIState:StateFlow<User?> = _userUIState

    private val _profileUIState = MutableStateFlow(ProfileUIState())
    val profileUIState:StateFlow<ProfileUIState> = _profileUIState

    private val _selectedImageUriUIState = MutableStateFlow<Uri?>(null)
    val selectedImageUriUIState:StateFlow<Uri?> = _selectedImageUriUIState

    private val _hasBeenExitUIState = MutableStateFlow(false)
    val hasBeenExitUIState:StateFlow<Boolean> = _hasBeenExitUIState

    init {
        fetchUser()
    }

    fun refreshData(){
        viewModelScope.launch {
            UseCases.useFullAppSync().execute()
            fetchUser()
        }
    }

    fun fetchUser(){
        viewModelScope.launch {
            _profileUIState.value = profileUIState.value.copy(isLoading = true)
            val user = UseCases.useGetUserData().execute().data
            _userUIState.value = user
            _profileUIState.value = profileUIState.value.copy(isLoading = false)
        }
    }

    fun onTitleChange(title:String){
        _profileUIState.value = profileUIState.value.copy(selectedLang = title)
        switchShowingPicker()
    }

    fun switchShowingPicker(){
        _profileUIState.value = profileUIState.value.copy(isPickerActive = profileUIState.value.isPickerActive.not())
    }

    fun uploadProfileImage(uri:Uri, context:Context){
        viewModelScope.launch {
            _selectedImageUriUIState.value = uri
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val bytes = stream.toByteArray()
            UseCases.useUploadUserPhoto().execute(userId = userUIState.value?.login ?: "", file = bytes)
        }
    }

    fun onNameChange(name:String){
        _profileUIState.value = profileUIState.value.copy(nameTextField = name)
    }

    fun onNumberChange(number:String){
        _profileUIState.value = profileUIState.value.copy(numberTextField = number)
    }

    fun switchShowingNameTextField(){
        _profileUIState.value = profileUIState.value.copy(isNameFormActive = !profileUIState.value.isNameFormActive)
        if(profileUIState.value.isNameFormActive.not()){
            viewModelScope.launch {
                _userUIState.value = userUIState.value?.copy(name = profileUIState.value.nameTextField)
                _profileUIState.value = profileUIState.value.copy(nameTextField = "")
                UseCases.useEditUser().execute(userUIState.value!!)
            }
        }
    }

    fun switchShowingNumber(){
        _profileUIState.value = profileUIState.value.copy(isNumberFormActive = !profileUIState.value.isNumberFormActive)
        if(profileUIState.value.isNumberFormActive.not()){
            viewModelScope.launch {
                _userUIState.value = userUIState.value?.copy(number = profileUIState.value.numberTextField)
                _profileUIState.value = profileUIState.value.copy(numberTextField = "")
                UseCases.useEditUser().execute(userUIState.value!!)
            }
        }
    }

    fun toQuit(){
        viewModelScope.launch {
            UseCases.useDeleteUser().execute()
            _hasBeenExitUIState.value = true
        }
    }

}