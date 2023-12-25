package com.example.coffeeshop.android.navigation

sealed class Screen(val route:String) {
    object StartScreen:Screen("start_screen")
    object LoginScreen:Screen("login_screen")
    object SplashScreen:Screen("splash_screen")
    object MainScreen:Screen("main_screen")
    object CoffeeDetailScreen:Screen("coffee_detail_screen")
    object FavoriteCoffeeScreen:Screen("favorite_coffee_screen")
    object CartScreen:Screen("cart_screen")
    object OrdersScreen:Screen("orders_screen")
    object ProfileScreen:Screen("profile_screen")
    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}