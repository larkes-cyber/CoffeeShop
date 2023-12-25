package com.example.coffeeshop.android.screen.profile_screen

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
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

    private val _userUIState = MutableStateFlow<User?>(null)
    val userUIState:StateFlow<User?> = _userUIState

    private val _profileUIState = MutableStateFlow(ProfileUIState())
    val profileUIState:StateFlow<ProfileUIState> = _profileUIState

    private val _selectedImageUriUIState = MutableStateFlow<Uri?>(null)
    val selectedImageUriUIState:StateFlow<Uri?> = _selectedImageUriUIState

    val languages = listOf("Russian", "English")


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

}