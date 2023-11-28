package com.example.coffeeshop.android.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.domain.model.Coffee

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    onPlusClick:() -> Unit = {},
    onMinusClick:() -> Unit = {},
    coffee: Coffee,
    getCoffeeImage:(String, MutableState<ImageBitmap?>) -> Unit,
    amount:Int
) {

    val image = remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    LaunchedEffect(Unit){
        getCoffeeImage(coffee.id, image)
    }


    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier.size(54.dp),
                contentAlignment = Alignment.Center
            ) {
                if(image.value != null) {
                    Image(
                        image.value!!,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }else{
                    CircularProgressIndicator()
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = coffee.categoryTitle,
                    fontSize = 16.sp,
                    color = AppTheme.colors.secondPrimaryTitle,
                    fontFamily = sora,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = coffee.subtitle,
                    fontSize = 12.sp,
                    color = AppTheme.colors.thirdGradientBackground,
                    fontFamily = sora,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            CircleButton(
                icon = R.drawable.minus
            ){
                onMinusClick()
            }
            Text(
                text = amount.toString(),
                modifier = Modifier.padding(horizontal = 14.dp),
                color = AppTheme.colors.secondPrimaryTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            CircleButton(
                icon = R.drawable.add
            ){
                onPlusClick()
            }
        }
        
    }
    
}
