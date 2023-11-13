package com.example.coffeeshop.android.screen.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.untils.Constants.HASNT_AUTH
import com.example.coffeeshop.android.untils.Constants.HAS_AUTH

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel
){

    val authUIState by viewModel.authUIState.collectAsState()
    LaunchedEffect(authUIState){
        if(authUIState == HAS_AUTH){
            navController.navigate(Screen.MainScreen.route)
        }else if(authUIState == HASNT_AUTH){
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