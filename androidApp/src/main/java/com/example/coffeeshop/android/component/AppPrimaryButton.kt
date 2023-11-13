package com.example.coffeeshop.android.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun AppPrimaryButton(
    modifier: Modifier = Modifier,
    title:String,
    onClick:() -> Unit = {}
) {

    Button(
        onClick = {
        onClick()
        },
        modifier = modifier.height(62.dp),
        colors = ButtonDefaults.buttonColors(containerColor = AppTheme.colors.thirdBackground),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = AppTheme.colors.primaryTitle,
            fontFamily = sora,
            fontWeight = FontWeight.SemiBold
        )
    }

}