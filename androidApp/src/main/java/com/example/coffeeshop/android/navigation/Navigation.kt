package com.example.coffeeshop.android.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coffeeshop.android.screen.login_screen.LoginScreen
import com.example.coffeeshop.android.screen.login_screen.LoginViewModel
import com.example.coffeeshop.android.screen.start_screen.StartScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(Screen.SplashScreen.route){

        }
        composable(Screen.StartScreen.route){
            StartScreen()
        }
        composable(Screen.LoginScreen.route){
            val viewModel:LoginViewModel = hiltViewModel()
            LoginScreen(navController = navController, viewModel = viewModel)
        }

    }

}