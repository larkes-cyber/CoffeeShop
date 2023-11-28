package com.example.coffeeshop.android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun TabButtons(
    modifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    currentTitle:String,
    titles:List<String>,
    onTitleClick:(String) -> Unit
) {

    Row(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(AppTheme.colors.fourthSubBackground)
    ) {
        titles.forEach { 
            Button(
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = if(it == currentTitle) AppTheme.colors.thirdBackground else Color.Transparent),
                shape = RoundedCornerShape(10.dp),
                modifier = buttonModifier.weight(1f),
                elevation = ButtonDefaults.elevation(0.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    fontFamily = sora,
                    fontWeight = FontWeight.SemiBold,
                    color = if(it == currentTitle) AppTheme.colors.primaryTitle else AppTheme.colors.primarySubtitle,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }

}