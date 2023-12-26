package com.example.coffeeshop.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshop.android.navigation.BottomNavBar
import com.example.coffeeshop.android.navigation.BottomNavItem
import com.example.coffeeshop.android.navigation.Navigation
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.CoffeeShopAppTheme
import com.example.coffeeshop.android.untils.Constants.CART_ICON
import com.example.coffeeshop.android.untils.Constants.FAVORITE_ICON
import com.example.coffeeshop.android.untils.Constants.HOME_ICON
import com.example.coffeeshop.android.untils.Constants.PROFILE_ICON
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewmodel: MainActivityViewModel by viewModels()

    override fun onStart() {
        super.onStart()



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            val navController = rememberNavController()

            CoffeeShopAppTheme {

                Box(modifier = Modifier.fillMaxSize()) {

                    Navigation(navController = navController)
                    Box(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        BottomNavBar(
                            items = listOf(
                                BottomNavItem(
                                    icon = R.drawable.home,
                                    name = HOME_ICON,
                                    route = Screen.MainScreen.route
                                ),
                                BottomNavItem(
                                    icon = R.drawable.heart,
                                    name = FAVORITE_ICON,
                                    route = Screen.FavoriteCoffeeScreen.route
                                ),
                                BottomNavItem(
                                    icon = R.drawable.cart,
                                    name = CART_ICON,
                                    route = Screen.CartScreen.route
                                ),
                                BottomNavItem(
                                    icon = R.drawable.baseline_person_24,
                                    name = PROFILE_ICON,
                                    route = Screen.ProfileScreen.route
                                )
                            ),
                            navController = navController
                        ){
                            navController.navigate(it.route)
                        }
                    }
                }
            }
        }
    }
}

