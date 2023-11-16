package com.example.coffeeshop.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.domain.model.Coffee

@Composable
fun FavoriteCoffeeItem(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    getCoffeeImage:(String, MutableState<ImageBitmap?>) -> Unit
    ) {

    val image = remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    LaunchedEffect(Unit){
        getCoffeeImage(coffee.id, image)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppTheme.colors.thirdSubBackground
        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 7.dp, bottom = 7.dp, end = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if(image.value != null){
                            Image(
                                image.value!!,
                                contentDescription = "",
                                modifier = Modifier.fillMaxSize()
                            )
                        }else{
                            CircularProgressIndicator()
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = coffee.categoryTitle,
                                fontSize = 15.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = coffee.subtitle,
                                fontSize = 12.sp,
                                color = AppTheme.colors.thirdGradientBackground,
                                fontFamily = sora,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "",
                                tint = Color.Red,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "$${coffee.price}",
                                fontSize = 12.sp,
                                color = AppTheme.colors.fourthPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                AddCartBtn{

                }
            }
        }
    }


}