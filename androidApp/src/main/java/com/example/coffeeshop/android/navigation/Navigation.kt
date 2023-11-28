package com.example.coffeeshop.android.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coffeeshop.android.screen.cart_screen.CartScreen
import com.example.coffeeshop.android.screen.cart_screen.CartViewModel
import com.example.coffeeshop.android.screen.coffee_detail_screen.CoffeeDetailScreen
import com.example.coffeeshop.android.screen.coffee_detail_screen.CoffeeDetailViewModel
import com.example.coffeeshop.android.screen.favorite_coffee_screen.FavoriteCoffeeScreen
import com.example.coffeeshop.android.screen.favorite_coffee_screen.FavoriteCoffeeViewModel
import com.example.coffeeshop.android.screen.login_screen.LoginScreen
import com.example.coffeeshop.android.screen.login_screen.LoginViewModel
import com.example.coffeeshop.android.screen.main_screen.MainScreen
import com.example.coffeeshop.android.screen.main_screen.MainViewModel
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
            val viewModel:MainViewModel = hiltViewModel()
            MainScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            Screen.CoffeeDetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ){entry ->
            val id = entry.arguments!!.getString("id")!!
            val viewModel:CoffeeDetailViewModel = hiltViewModel()
            CoffeeDetailScreen(navController = navController, viewModel = viewModel, id = id)
        }

        composable(Screen.FavoriteCoffeeScreen.route){
            val viewModel:FavoriteCoffeeViewModel = hiltViewModel()
            FavoriteCoffeeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.OrdersScreen.route){

        }

        composable(Screen.CartScreen.route){
            val viewModel:CartViewModel = hiltViewModel()
            CartScreen(navController = navController, viewModel = viewModel)
        }

    }

}