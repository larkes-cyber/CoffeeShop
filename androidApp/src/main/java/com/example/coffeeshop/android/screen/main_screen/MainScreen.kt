package com.example.coffeeshop.android.screen.main_screen

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.component.CategoryItem
import com.example.coffeeshop.android.component.CoffeeCart
import com.example.coffeeshop.android.component.SearchBar
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
) {

    val mainScreenUIState by viewModel.mainScreenUIState.collectAsState()
    val searchCoffeeUIState by viewModel.searchCoffeeUIState.collectAsState()
    val userUIState by viewModel.userUIState.collectAsState()
    val hasBeenExit by viewModel.hasBeenExit.collectAsState()

    LaunchedEffect(Unit){
        viewModel.loadCategories().join()
        viewModel.loadCoffee().join()
        viewModel.loadUserData()
    }

    LaunchedEffect(hasBeenExit){
        if(hasBeenExit){
            navController.navigate(Screen.SplashScreen.route)
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = mainScreenUIState.isCategoriesLoading || mainScreenUIState.isCoffeeLoading),
        onRefresh = {
            viewModel.syncCoffee()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.secondBackground)
        ) {
            item{
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

                            if(userUIState.user != null) {
                                Text(
                                    text = userUIState.user!!.name,
                                    fontFamily = sora,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = AppTheme.colors.thirdPrimaryTitle
                                )
                                ClickableText(
                                    text = AnnotatedString("exit"),
                                    onClick = {
                                        viewModel.deleteUser()
                                    },
                                    style = TextStyle(
                                        fontFamily = sora,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 12.sp,
                                        color = AppTheme.colors.thirdBackground
                                    )
                                )
                            }

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
                        text = searchCoffeeUIState.symbols
                    ){
                        viewModel.searchForCoffee(it)
                    }
                    if(searchCoffeeUIState.searchMode.not()) {
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

                }
            }

            item {
                Column(
                    modifier = Modifier
                        .background(AppTheme.colors.secondBackground)
                ) {
                    if (searchCoffeeUIState.searchMode.not()) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            if (mainScreenUIState.categories.isNotEmpty()) {
                                LazyRow(
                                    modifier = Modifier
                                        .height(38.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    item {
                                        Spacer(modifier = Modifier.width(30.dp))
                                    }
                                    itemsIndexed(mainScreenUIState.categories) { index, item ->
                                        CategoryItem(
                                            title = item.title,
                                            isSelected = mainScreenUIState.selectedCategory == item.id
                                        ) {
                                            viewModel.changeCategory(item.id)
                                        }
                                    }
                                }
                            }
                            if (mainScreenUIState.isCategoriesLoading) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(28.dp))
                }
            }

            items(viewModel.prepareCoffeeData(mainScreenUIState.coffee),{it.first().id}){coffeePair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    coffeePair.forEach { coffee ->
                        Box(modifier = Modifier.weight(1f)) {
                            CoffeeCart(
                                coffee = coffee,
                                getCoffeeImage = {id, state ->
                                    viewModel.getCoffeeImage(id, state)
                                },
                                onCartBtnClick = {
                                    viewModel.addToCart(id = coffee.id)
                                }
                            ){
                                navController.navigate(Screen.CoffeeDetailScreen.withArgs(coffee.id))
                            }
                        }
                        if(coffeePair.size == 1){
                            Box(modifier = Modifier.weight(1f))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                if(mainScreenUIState.isCoffeeLoading){
                    CircularProgressIndicator()
                }
            }
            item{
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}