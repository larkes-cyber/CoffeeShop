package com.example.coffeeshop.android.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.untils.Constants.COFFEE_PHOTOS_URL
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CoffeeCard(
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
        backgroundColor = AppTheme.colors.thirdSubBackground,
        shape = RoundedCornerShape(16.dp),
        onClick = {
            onClick()
        }
    ){
        Box(modifier = Modifier.fillMaxWidth()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .padding(top = 4.dp, bottom = 12.dp)
            ) {
                AsyncImage(
                    model = COFFEE_PHOTOS_URL + coffee.id,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(132.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))
                Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Text(
                        text = coffee.categoryTitle,
                        fontSize = 16.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = coffee.subtitle,
                        fontSize = 12.sp,
                        color = AppTheme.colors.thirdGradientBackground,
                        fontFamily = sora,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "$${coffee.price}",
                            fontSize = 18.sp,
                            color = AppTheme.colors.fourthPrimaryTitle,
                            fontFamily = sora,
                            fontWeight = FontWeight.SemiBold
                        )
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

    }

}