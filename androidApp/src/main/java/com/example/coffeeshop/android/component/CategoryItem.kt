package com.example.coffeeshop.android.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun CategoryItem(
    title:String,
    isSelected:Boolean,
    onClick:() -> Unit = {}
){

    Button(
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor =  if(isSelected) AppTheme.colors.thirdBackground else AppTheme.colors.thirdSubBackground),
        elevation = ButtonDefaults.elevation(0.dp),
        onClick = {
            onClick()
        },
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontFamily = sora,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = if(isSelected) AppTheme.colors.primaryTitle else AppTheme.colors.fourthPrimaryTitle
            )
        }
    }

}