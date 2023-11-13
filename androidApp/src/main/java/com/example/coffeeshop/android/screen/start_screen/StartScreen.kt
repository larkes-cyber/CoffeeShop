package com.example.coffeeshop.android.screen.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.component.AppPrimaryButton
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.GET_STARTED_BTN_TITLE
import com.example.coffeeshop.android.untils.Constants.START_SCREEN_SUBTITLE
import com.example.coffeeshop.android.untils.Constants.START_SCREEN_TITLE

@Composable
fun StartScreen(
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(AppTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.coffee_splash),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = 30.dp)) {
            Text(
                text = START_SCREEN_TITLE,
                fontFamily = sora,
                fontWeight = FontWeight.SemiBold,
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.primaryTitle
                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = START_SCREEN_SUBTITLE,
                fontFamily = sora,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = AppTheme.colors.subtitle,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            AppPrimaryButton(
                title = GET_STARTED_BTN_TITLE,
                modifier = Modifier.fillMaxWidth()
            ){
                navController.navigate(Screen.LoginScreen.route)
            }
            Spacer(modifier = Modifier.height(30.dp))

        }
    }

}