package com.example.coffeeshop.android.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun OutlinedAppPrimaryButton(
    modifier:Modifier = Modifier,
    title:String = "",
    icon:@Composable () -> Unit = {},
    onClick:() -> Unit = {}
) {
    
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        border = BorderStroke(1.dp, AppTheme.colors.thirdBackground),
        contentPadding = PaddingValues(0.dp),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.height(62.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            icon()
            Text(
                text = title,
                fontSize = 16.sp,
                color = AppTheme.colors.thirdBackground,
                fontFamily = sora,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
    
}