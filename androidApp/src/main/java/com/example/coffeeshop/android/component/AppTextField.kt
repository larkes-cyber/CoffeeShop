package com.example.coffeeshop.android.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier:Modifier = Modifier,
    text:String,
    hint:String = "",
    placeholder:String = "",
    singleLine:Boolean = false,
    onChange:(String) -> Unit
) {

    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    OutlinedTextField(
        value = text, 
        onValueChange = {
            onChange(it)
        },
        singleLine = singleLine,
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = sora,
                fontSize = 14.sp,
                color = AppTheme.colors.textField,
                fontWeight = FontWeight.Normal
            )
        },
        label = {
            Text(
                text = hint,
                fontSize = 12.sp,
                color = AppTheme.colors.textField,
                fontFamily = sora,
                fontWeight = FontWeight.Normal
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusEvent {
                if (it.isFocused) {
                    scope.launch {
                        delay(200)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        colors = TextFieldDefaults.colors(
            cursorColor = AppTheme.colors.primaryTitle,
            unfocusedIndicatorColor = AppTheme.colors.primaryTitle,
            focusedTextColor = AppTheme.colors.primaryTitle,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = AppTheme.colors.primaryTitle,
            unfocusedTextColor = AppTheme.colors.primaryTitle
        )
    )


}