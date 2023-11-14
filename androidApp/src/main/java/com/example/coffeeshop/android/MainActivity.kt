package com.example.coffeeshop.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshop.BusinessModule
import com.example.coffeeshop.android.navigation.BottomNavBar
import com.example.coffeeshop.android.navigation.BottomNavItem
import com.example.coffeeshop.android.navigation.Navigation
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.CoffeeShopAppTheme
import com.example.coffeeshop.android.untils.Constants.CART_ICON
import com.example.coffeeshop.android.untils.Constants.FAVORITE_ICON
import com.example.coffeeshop.android.untils.Constants.HOME_ICON
import com.example.coffeeshop.android.untils.Constants.NOTIFICATIONS_ICON
import com.example.coffeeshop.di.UseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BusinessModule(this).init()

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
                                    route = Screen.MainScreen.route
                                ),
                                BottomNavItem(
                                    icon = R.drawable.cart,
                                    name = CART_ICON,
                                    route = Screen.MainScreen.route
                                ),
                                BottomNavItem(
                                    icon = R.drawable.notification,
                                    name = NOTIFICATIONS_ICON,
                                    route = Screen.MainScreen.route
                                ),
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

