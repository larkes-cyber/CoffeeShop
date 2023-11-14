package com.example.coffeeshop.android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.SEARCH_COFFEE_TITLE

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text:String,
    onChange:(String) -> Unit = {}
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(AppTheme.colors.secondGradientBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "",
            modifier = Modifier.size(20.dp),
            tint = AppTheme.colors.secondBackground
        )
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            value = text,
            onValueChange = {
                onChange(it)
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = sora,
                fontWeight = FontWeight.Normal,
                color = AppTheme.colors.secondSubtitle
            ),
            decorationBox = {
                if(text.isEmpty()){
                   Text(
                       text = SEARCH_COFFEE_TITLE,
                       style = TextStyle(
                           fontSize = 14.sp,
                           fontFamily = sora,
                           fontWeight = FontWeight.Normal,
                           color = AppTheme.colors.thirdPrimaryTitle
                       )
                   )
                }
                it()
            }
        )
    }

}