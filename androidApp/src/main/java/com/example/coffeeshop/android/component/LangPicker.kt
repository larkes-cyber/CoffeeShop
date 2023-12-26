package com.example.coffeeshop.android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora

@Composable
fun ItemsPicker(
    modifier: Modifier = Modifier,
    currentTitle:String,
    options:List<String>,
    onClick:(String) -> Unit
) {

    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach {title ->
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "",
                        tint = AppTheme.colors.secondPrimaryTitle.copy(alpha = if(title == currentTitle) 1f else 0f),
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    ClickableText(
                        text = AnnotatedString(title),
                        onClick = {
                            onClick(title)
                        },
                        style = TextStyle(
                            fontFamily = sora,
                            color = AppTheme.colors.secondPrimaryTitle,
                            fontSize = 14.sp
                        ),
                        modifier = modifier
                    )
                }
            }
        }
    }

}
