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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle

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
import coil.compose.SubcomposeAsyncImage
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.component.CategoryItem
import com.example.coffeeshop.android.component.CoffeeCard
import com.example.coffeeshop.android.component.SearchBar
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.EXIT_TITLE
import com.example.coffeeshop.untils.Constants
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


    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = mainScreenUIState.isCategoriesLoading || mainScreenUIState.isCoffeeLoading),
        onRefresh = {
            viewModel.refreshData()
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
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        if(userUIState.user != null) {
                            Column(horizontalAlignment = Alignment.Start) {

                                Text(
                                    text = "Welcome",
                                    fontFamily = sora,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = AppTheme.colors.thirdPrimaryTitle
                                )
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = userUIState.user!!.name,
                                    fontFamily = sora,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 12.sp,
                                    color = AppTheme.colors.thirdPrimaryTitle
                                )
                            }
                        }
                        SubcomposeAsyncImage(
                            model =  Constants.USER_PHOTO_URL + userUIState.user?.login,
                            contentDescription = "",
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(14.dp)),
                            contentScale = ContentScale.Crop,
                            loading = {
                                Box(
                                    modifier = Modifier.size(44.dp)
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
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
                            CoffeeCard(
                                coffee = coffee,
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
            item{
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}