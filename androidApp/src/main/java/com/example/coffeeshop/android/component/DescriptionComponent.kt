package com.example.coffeeshop.android.component

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.HIDE_TITLE
import com.example.coffeeshop.android.untils.Constants.READ_MORE_TITLE

@Composable
fun DescriptionComponent(
    description:String
) {
    val readMoreMode = remember {
        mutableStateOf(false)
    }

    val shortedText = if(description.length > 116) description.substring(0, 115) + "..." else description

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontSize = 14.sp,
            color = AppTheme.colors.fifthPrimaryTitle,
            fontWeight = FontWeight.Normal,
            fontFamily = sora)){
            append(if(readMoreMode.value) description else shortedText)
        }
        pushStringAnnotation(tag = "read_more", annotation = "read_more")
        withStyle(
            style = SpanStyle(
                fontSize = 14.sp,
                color = AppTheme.colors.thirdBackground,
                fontWeight = FontWeight.Normal,
                fontFamily = sora
            )){
            append(" " + if(readMoreMode.value) HIDE_TITLE else READ_MORE_TITLE)
        }
    }
    
    ClickableText(text = annotatedString,
        onClick = {offset ->
            annotatedString.getStringAnnotations(tag = "read_more", start = offset, end = offset).firstOrNull().let {
                readMoreMode.value = !readMoreMode.value
            }
        }
    )


}