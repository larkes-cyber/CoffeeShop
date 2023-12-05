package com.example.coffeeshop.android.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.untils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteCoffeeItem(
    modifier: Modifier = Modifier,
    coffee: Coffee,
    onCartBtnClick:() -> Unit,
    onClick:() -> Unit
    ) {

    val coroutineScope = rememberCoroutineScope()


    var cartBtnHasClickedUIState by remember {
        mutableStateOf(false)
    }

    val transition = updateTransition(
        targetState = cartBtnHasClickedUIState,
        label = ""
    )

    val rotateCartButton by transition.animateFloat(
        transitionSpec = {
            tween(
                durationMillis = 400,
                easing = FastOutLinearInEasing
            )
        },
        label = "",
        targetValueByState = {cartHasClicked ->
            if(cartHasClicked) 360f else 0f
        }
    )



    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = AppTheme.colors.thirdSubBackground,
        onClick = {
            onClick()
        }
        ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, start = 7.dp, bottom = 7.dp, end = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Row {
                    AsyncImage(
                        model = Constants.COFFEE_PHOTOS_URL + coffee.id,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(120.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxHeight()
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
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "",
                                tint = Color.Red,
                                modifier = Modifier.size(16.dp)
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
                IncCartBtn(
                    imageVector = if(cartBtnHasClickedUIState) Icons.Default.Check else Icons.Default.Add,
                    modifier = Modifier.rotate(rotateCartButton)
                ){
                    if(cartBtnHasClickedUIState.not()){
                        coroutineScope.launch {
                            cartBtnHasClickedUIState = true
                            onCartBtnClick()
                            delay(3000)
                            cartBtnHasClickedUIState = false
                        }
                    }
                }
            }
        }
    }
}