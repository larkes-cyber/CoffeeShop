package com.example.coffeeshop.android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.coffeeshop.android.theme.AppTheme

@Composable
fun SimpleTextField(
    modifier:Modifier = Modifier,
    text:String,
    textStyle: TextStyle = TextStyle(),
    placeholder: String = "",
    onTextChange:(String) -> Unit
    ) {

    Column {
        Box {
            BasicTextField(
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                modifier = modifier,
                textStyle = textStyle
            )
            if(text.isEmpty()){
                Text(
                    text = placeholder,
                    style = textStyle
                )
            }
        }
        Divider(
            modifier = modifier.height(1.dp).background(AppTheme.colors.strokeColor)
        )
    }


}