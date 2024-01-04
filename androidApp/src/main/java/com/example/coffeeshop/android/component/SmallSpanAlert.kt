package com.example.coffeeshop.android.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun SmallSpanAlert(
    modifier:Modifier = Modifier,
    title:String,
    isActive:Boolean,
    onClick:() -> Unit
) {

    AnimatedVisibility(visible = isActive) {
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(15))
                .background(AppTheme.colors.thirdSubBackground)
                .padding(horizontal = 8.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = AppTheme.colors.secondPrimaryTitle,
                fontSize = 14.sp,
                fontFamily = sora,
                fontWeight = FontWeight.Normal
            )

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = AppTheme.colors.secondPrimaryTitle,
                modifier = Modifier
                    .size(18.dp)
                    .clickable {
                    onClick()
                }
            )

        }
    }


}