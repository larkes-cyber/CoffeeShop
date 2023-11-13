package com.example.coffeeshop.android.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.PASSWORD_PLACEHOLDER
import com.example.coffeeshop.android.untils.Constants.PASSWORD_TITLE

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password:String,
    onChange:(String) -> Unit
) {

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    OutlinedTextField(
            value = password,
            onValueChange = {
               onChange(it)
            },
            placeholder = {
                Text(
                text = PASSWORD_PLACEHOLDER,
                fontFamily = sora,
                fontSize = 14.sp,
                color = AppTheme.colors.textField,
                fontWeight = FontWeight.Normal
                )
            },
            label = {
                Text(
                text = PASSWORD_TITLE,
                fontSize = 12.sp,
                color = AppTheme.colors.textField,
                fontFamily = sora,
                fontWeight = FontWeight.Normal
                )
            },
            modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.colors(
                cursorColor = AppTheme.colors.primaryTitle,
                unfocusedIndicatorColor = AppTheme.colors.primaryTitle,
                focusedTextColor = AppTheme.colors.primaryTitle,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = AppTheme.colors.primaryTitle,
                unfocusedTextColor = AppTheme.colors.primaryTitle
                ),
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible.value)
                    R.drawable.baseline_visibility_24
                else R.drawable.baseline_visibility_off_24
                val description = if (passwordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = {passwordVisible.value = !passwordVisible.value}){
                    Icon(
                        painter = painterResource(id = image),
                        description,
                        tint = AppTheme.colors.textField
                    )
                }
            }
        )

}