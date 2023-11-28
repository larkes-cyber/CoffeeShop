package com.example.coffeeshop.android.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.android.theme.AppTheme

@Composable
fun CircleButton(
    icon:Int,
    onClick:() -> Unit
) {

    Button(
        onClick = {onClick() },
        modifier = Modifier.size(28.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(6.dp),
        border = BorderStroke(1.dp, AppTheme.colors.borderColor)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(16.dp),
            tint = AppTheme.colors.secondPrimaryTitle
        )
    }

}