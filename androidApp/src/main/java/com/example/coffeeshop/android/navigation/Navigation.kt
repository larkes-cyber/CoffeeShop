package com.example.coffeeshop.android.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coffeeshop.android.screen.login_screen.LoginScreen
import com.example.coffeeshop.android.screen.login_screen.LoginViewModel
import com.example.coffeeshop.android.screen.main_screen.MainScreen
import com.example.coffeeshop.android.screen.splash_screen.SplashScreen
import com.example.coffeeshop.android.screen.splash_screen.SplashViewModel
import com.example.coffeeshop.android.screen.start_screen.StartScreen

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(Screen.SplashScreen.route){
            val viewModel:SplashViewModel = hiltViewModel()
            SplashScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(Screen.StartScreen.route){
            StartScreen(
                navController = navController
            )
        }
        composable(Screen.LoginScreen.route){
            val viewModel:LoginViewModel = hiltViewModel()
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }

    }

}