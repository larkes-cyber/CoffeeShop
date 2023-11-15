package com.example.coffeeshop.android.navigation

sealed class Screen(val route:String) {
    object StartScreen:Screen("start_screen")
    object LoginScreen:Screen("login_screen")
    object SplashScreen:Screen("splash_screen")
    object MainScreen:Screen("main_screen")
    object CoffeeDetailScreen:Screen("coffee_detail_screen")
    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}