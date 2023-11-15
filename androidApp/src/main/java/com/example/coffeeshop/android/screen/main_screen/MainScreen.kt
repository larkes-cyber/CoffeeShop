package com.example.coffeeshop.android.screen.main_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.component.CategoryItem
import com.example.coffeeshop.android.component.CoffeeCart
import com.example.coffeeshop.android.component.SearchBar
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.WELCOME_TITLE
import com.example.coffeeshop.di.UseCases

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val mainScreenUIState by viewModel.mainScreenUIState.collectAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            AppTheme.colors.secondGradientBackground,
                            AppTheme.colors.firstGradientBackground

                        )
                    )
                )
                .padding(horizontal = 30.dp)
                .padding(vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = WELCOME_TITLE,
                        fontFamily = sora,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = AppTheme.colors.secondSubtitle
                    )
                    Text(
                        text = "Lora Roberts",
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = AppTheme.colors.thirdPrimaryTitle
                    )
                }
                Image(
                    painter = painterResource(id =R.drawable.ton_bird),
                    contentDescription = "",
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                text = ""
            ){

            }
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = R.drawable.promote),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.secondBackground)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                if(mainScreenUIState.categories.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier
                            .height(38.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                        itemsIndexed(mainScreenUIState.categories) { index, item ->
                            CategoryItem(title = item.title, isSelected = mainScreenUIState.selectedCategory == item.id){
                                viewModel.changeCategory(item.id)
                            }
                        }
                    }
                }
                if(mainScreenUIState.isCategoriesLoading){
                    CircularProgressIndicator()
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), contentAlignment = Alignment.Center) {
                if(mainScreenUIState.coffee.isNotEmpty()) {
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        viewModel.prepareCoffeeData(mainScreenUIState.coffee)
                            .forEach { coffeePair ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    coffeePair.forEach { coffee ->
                                        Box(modifier = Modifier.weight(1f)) {
                                            CoffeeCart(coffee = coffee)
                                        }
                                        if(coffeePair.size == 1){
                                            Box(modifier = Modifier.weight(1f))
                                        }
                                    }
                                }
                            }
                    }
                }
                if(mainScreenUIState.isCoffeeLoading){
                    CircularProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.height(150.dp))
        }
    }


}