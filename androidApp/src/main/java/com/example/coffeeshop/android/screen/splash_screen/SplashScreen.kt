package com.example.coffeeshop.android.screen.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.untils.Constants.NOT_AUTH
import com.example.coffeeshop.android.untils.Constants.AUTH_SUCCESS

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
){

    val authUIState by viewModel.authUIState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.init()
    }

    LaunchedEffect(authUIState){
        if(authUIState == AUTH_SUCCESS){
            navController.navigate(Screen.MainScreen.route)
        }else if(authUIState == NOT_AUTH){
            navController.navigate(Screen.StartScreen.route)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }

}