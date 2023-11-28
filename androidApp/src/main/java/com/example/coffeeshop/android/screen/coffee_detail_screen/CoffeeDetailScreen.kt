package com.example.coffeeshop.android.screen.coffee_detail_screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.component.AppPrimaryButton
import com.example.coffeeshop.android.component.CoffeeSizeBtn
import com.example.coffeeshop.android.component.DescriptionComponent
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.BUY_NOW_TITLE
import com.example.coffeeshop.android.untils.Constants.DETAIL_MAIN_SCREEN_DESCR_TITLE
import com.example.coffeeshop.android.untils.Constants.DETAIL_MAIN_SCREEN_TITLE
import com.example.coffeeshop.android.untils.Constants.LARGE_COFFEE_SIZE
import com.example.coffeeshop.android.untils.Constants.MEDIUM_COFFEE_SIZE
import com.example.coffeeshop.android.untils.Constants.PRICE_TITLE
import com.example.coffeeshop.android.untils.Constants.SIZE_TITLE
import com.example.coffeeshop.android.untils.Constants.SMALL_COFFEE_SIZE

@Composable
fun CoffeeDetailScreen(
    navController: NavController,
    viewModel: CoffeeDetailViewModel,
    id:String,
) {

    val coffeeDetailUIState by viewModel.coffeeDetailUIState.collectAsState()

    val scrollState = rememberScrollState()

    LaunchedEffect(Unit){
        viewModel.getCoffee()
        viewModel.pullCoffeeImage()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.secondBackground)
            .padding(top = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.secondBackground)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = AppTheme.colors.secondPrimaryTitle,
                        modifier = Modifier.size(34.dp)
                    )
                }
                Text(
                    text = DETAIL_MAIN_SCREEN_TITLE,
                    fontSize = 18.sp,
                    fontFamily = sora,
                    fontWeight = FontWeight.SemiBold,
                    color = AppTheme.colors.secondPrimaryTitle
                )
                IconButton(onClick = {
                    viewModel.switchCoffeeFavorite()
                }) {
                    Icon(
                        imageVector = if(coffeeDetailUIState.coffeeIsFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "",
                        tint = if(coffeeDetailUIState.coffeeIsFavorite) Color.Red else AppTheme.colors.secondPrimaryTitle,
                        modifier = Modifier.size(28.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(226.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if(coffeeDetailUIState.image == null){
                        CircularProgressIndicator()
                    }else {
                        Image(
                            coffeeDetailUIState.image!!,
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            if(coffeeDetailUIState.coffee != null){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Row {
                        Column() {
                            Text(
                                text = coffeeDetailUIState.coffee!!.categoryTitle,
                                fontSize = 20.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = sora
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = coffeeDetailUIState.coffee!!.subtitle,
                                fontSize = 12.sp,
                                color = AppTheme.colors.fifthPrimaryTitle,
                                fontWeight = FontWeight.Normal,
                                fontFamily = sora
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "",
                                    tint = Color(0xFFFBBE21),
                                    modifier = Modifier.size(21.dp)
                                )
                                Text(
                                    text = coffeeDetailUIState.coffee!!.price.toString(),
                                    fontSize = 16.sp,
                                    color = AppTheme.colors.secondPrimaryTitle,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = sora
                                )
                                Text(
                                    text = "(${coffeeDetailUIState.coffee!!.scoreCount})",
                                    fontSize = 12.sp,
                                    color = AppTheme.colors.fifthPrimaryTitle,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = sora
                                )
                            }
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        OptionIconWrapper(R.drawable.beans)
                        OptionIconWrapper(R.drawable.coffee)
                    }
                }

                Column(modifier = Modifier.padding(horizontal = 30.dp)) {
                    Box(modifier = Modifier.padding(vertical = 20.dp)) {
                        Divider(modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(AppTheme.colors.strokeColor))
                    }
                    Text(
                        text = DETAIL_MAIN_SCREEN_DESCR_TITLE,
                        fontSize = 16.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = sora
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DescriptionComponent(coffeeDetailUIState.coffee!!.description)
                    Text(
                        text = SIZE_TITLE,
                        fontSize = 16.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = sora,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        (0..2).forEach {
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                CoffeeSizeBtn(
                                    isSelected = coffeeDetailUIState.selectedCoffeeSize == it,
                                    text = when(it){
                                        0 -> SMALL_COFFEE_SIZE
                                        1 -> MEDIUM_COFFEE_SIZE
                                        2 -> LARGE_COFFEE_SIZE
                                        else -> ""
                                    }
                                ){
                                    viewModel.switchCoffeeSize(it)
                                }
                            }
                        }
                    }
                }
            }
        }
        if(coffeeDetailUIState.coffee != null){
            Column {
                Spacer(modifier = Modifier.height(15.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    backgroundColor = AppTheme.colors.thirdSubBackground
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .padding(top = 16.dp, bottom = 30.dp)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = PRICE_TITLE,
                                fontSize = 14.sp,
                                color = AppTheme.colors.fifthPrimaryTitle,
                                fontWeight = FontWeight.Normal,
                                fontFamily = sora
                            )
                            Text(
                                text = "$ ${coffeeDetailUIState.coffee!!.price}",
                                fontSize = 18.sp,
                                color = AppTheme.colors.thirdBackground,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = sora
                            )
                        }
                        AppPrimaryButton(
                            title = BUY_NOW_TITLE,
                            modifier = Modifier.weight(1.4f)
                        ){

                        }
                    }
                }
            }
        }


    }


}


@Composable
fun OptionIconWrapper(icon:Int) {

    Card(
        backgroundColor = AppTheme.colors.subBackground,
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(icon),
                contentDescription = "",
                modifier = Modifier.size(22.dp),
                contentScale = ContentScale.Crop
            )
        }
    }

}