package com.example.coffeeshop.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coffeeshop.android.screen.start_screen.StartScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.StartScreen.route){
        composable(Screen.SplashScreen.route){

        }
        composable(Screen.StartScreen.route){
            StartScreen()
        }

    }

}