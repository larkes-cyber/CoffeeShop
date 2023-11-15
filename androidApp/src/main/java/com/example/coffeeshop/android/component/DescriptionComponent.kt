package com.example.coffeeshop.android.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun DescriptionComponent(
    description:String
) {
    val readMoreMode = remember {
        mutableStateOf(false)
    }

    Text(
        text = description,
        fontSize = 14.sp,
        color = AppTheme.colors.fifthPrimaryTitle,
        fontWeight = FontWeight.Normal,
        fontFamily = sora
    )

}