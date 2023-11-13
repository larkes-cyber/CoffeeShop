package com.example.coffeeshop.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshop.BusinessModule
import com.example.coffeeshop.android.navigation.Navigation
import com.example.coffeeshop.android.theme.CoffeeShopAppTheme
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
                Navigation(navController = navController)

            }
        }
    }
}

