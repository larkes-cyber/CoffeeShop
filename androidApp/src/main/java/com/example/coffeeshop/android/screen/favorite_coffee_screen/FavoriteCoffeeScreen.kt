package com.example.coffeeshop.android.screen.favorite_coffee_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.component.FavoriteCoffeeItem
import com.example.coffeeshop.android.component.SearchBar
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.FAVORITE_COFFEE_TITLE

@Composable
fun FavoriteCoffeeScreen(
    navController: NavController,
    viewModel:FavoriteCoffeeViewModel
) {

    val favoriteCoffeeUIState by viewModel.favoriteCoffeeUIState.collectAsState()
    val searchCoffeeUIState by viewModel.searchCoffeeUIState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getFavoriteCoffee()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.secondBackground)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        AppTheme.colors.secondGradientBackground,
                        AppTheme.colors.firstGradientBackground
                    )
                )
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 20.dp, bottom = 50.dp)
            ) {
                Text(
                    text = FAVORITE_COFFEE_TITLE,
                    fontFamily = sora,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    color = AppTheme.colors.thirdPrimaryTitle
                )
                Spacer(modifier = Modifier.height(40.dp))
                SearchBar(
                    text = searchCoffeeUIState.symbols,
                    modifier = Modifier.fillMaxWidth()
                ){
                    viewModel.onSearchText(it)
                }
            }
        }
        Spacer(modifier = Modifier.height(19.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            itemsIndexed(favoriteCoffeeUIState.coffee){index, item ->
                FavoriteCoffeeItem(
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth(),
                    coffee = item,
                    onCartBtnClick = { viewModel.onAddCartChange(item.id) }
                ){
                    navController.navigate(Screen.CoffeeDetailScreen.withArgs(item.id))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }

}