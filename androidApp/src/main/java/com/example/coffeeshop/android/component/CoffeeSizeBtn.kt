package com.example.coffeeshop.android.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun CoffeeSizeBtn(
    text:String,
    isSelected:Boolean,
    onClick:() -> Unit = {}
) {

    Button(
        onClick = {
            onClick()
        },
        border = BorderStroke(
            width = 1.dp,
            color = if(isSelected) AppTheme.colors.thirdBackground else AppTheme.colors.strokeColor
        ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if(isSelected) AppTheme.colors.smallBtnColor else AppTheme.colors.thirdSubBackground),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(43.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = sora,
            fontWeight = FontWeight.Normal,
            color = if(isSelected) AppTheme.colors.thirdBackground else AppTheme.colors.secondPrimaryTitle
        )
    }

}