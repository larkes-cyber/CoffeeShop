package com.example.coffeeshop.android.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.android.theme.AppTheme

@Composable
fun IncCartBtn(
    icon:Int,
    onClick:() -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.size(32.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.thirdBackground),
        elevation = ButtonDefaults.elevation(0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(15.dp),
            tint = AppTheme.colors.primaryTitle
        )
    }
}