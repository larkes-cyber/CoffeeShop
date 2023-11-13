package com.example.coffeeshop.android.screen.login_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.component.AppPrimaryButton
import com.example.coffeeshop.android.component.AppTextField
import com.example.coffeeshop.android.component.PasswordTextField
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.ALREADY_HAVE_ACCOUNT
import com.example.coffeeshop.android.untils.Constants.AUTH_SUBTITLE
import com.example.coffeeshop.android.untils.Constants.AUTH_TITLE
import com.example.coffeeshop.android.untils.Constants.CONTINUE_BTN_TITLE
import com.example.coffeeshop.android.untils.Constants.DONT_HAVE_ACCOUNT
import com.example.coffeeshop.android.untils.Constants.ENTER_LOGIN_PLACEHOLDER
import com.example.coffeeshop.android.untils.Constants.ENTER_LOGIN_TITLE
import com.example.coffeeshop.android.untils.Constants.NAME_PLACEHOLDER
import com.example.coffeeshop.android.untils.Constants.NAME_TITLE
import com.example.coffeeshop.android.untils.Constants.REGISTRATION_MODE
import com.example.coffeeshop.android.untils.Constants.REG_TITLE
import com.example.coffeeshop.android.untils.Constants.SIGN_IN_TITLE
import com.example.coffeeshop.android.untils.Constants.SIGN_UP_TITLE

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
){

    val loginUIState by viewModel.loginUIState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text =if(loginUIState.mode == REGISTRATION_MODE) REG_TITLE else AUTH_TITLE,
                fontSize = 34.sp,
                color = AppTheme.colors.primaryTitle,
                fontFamily = sora,
                fontWeight = FontWeight.SemiBold
            )

            if(loginUIState.mode == REGISTRATION_MODE){
                Spacer(modifier = Modifier.height(53.dp))
            }else{
                Spacer(modifier = Modifier.height(13.dp))
                Text(
                    text = AUTH_SUBTITLE,
                    fontSize = 18.sp,
                    color = AppTheme.colors.subtitle,
                    fontFamily = sora,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(40.dp))
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                AppTextField(
                    text = loginUIState.login,
                    placeholder = ENTER_LOGIN_PLACEHOLDER,
                    hint = ENTER_LOGIN_TITLE,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    viewModel.onLoginChange(it)
                }
                PasswordTextField(
                    password = loginUIState.password,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    viewModel.onPasswordChange(it)
                }

                if(loginUIState.mode == REGISTRATION_MODE){
                    AppTextField(
                        text = loginUIState.name,
                        placeholder = NAME_PLACEHOLDER,
                        hint = NAME_TITLE,
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        viewModel.onNameChange(it)
                    }
                }

            }
            Spacer(modifier = Modifier.height(55.dp))
            AppPrimaryButton(
                title = CONTINUE_BTN_TITLE,
                modifier = Modifier.fillMaxWidth()
            ){

            }
            Spacer(modifier = Modifier.height(18.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text =if(loginUIState.mode == REGISTRATION_MODE) ALREADY_HAVE_ACCOUNT else DONT_HAVE_ACCOUNT,
                        fontSize = 14.sp,
                        fontFamily = sora,
                        fontWeight = FontWeight.Normal,
                        color = AppTheme.colors.subtitle
                    )
                    ClickableText(
                        text = AnnotatedString(if(loginUIState.mode == REGISTRATION_MODE) SIGN_IN_TITLE else SIGN_UP_TITLE),
                        style = TextStyle(
                            color = AppTheme.colors.thirdBackground,
                            fontSize = 14.sp,
                            fontFamily = sora,
                            fontWeight = FontWeight.Normal
                        )
                    ){
                        viewModel.switchMode()
                    }
                }
            }

        }
    }

}