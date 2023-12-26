package com.example.coffeeshop.android.screen.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshop.android.R
import com.example.coffeeshop.android.component.AppPrimaryButton
import com.example.coffeeshop.android.component.CartItem
import com.example.coffeeshop.android.component.MapView
import com.example.coffeeshop.android.component.SmallButton
import com.example.coffeeshop.android.component.TabButtons
import com.example.coffeeshop.android.navigation.Screen
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants
import com.example.coffeeshop.android.untils.Constants.ADD_ADDRESS_TITLE_BTN
import com.example.coffeeshop.android.untils.Constants.ADD_NOTE_TITLE_BTN
import com.example.coffeeshop.android.untils.Constants.DELIVERY_ADDRESS_TITLE
import com.example.coffeeshop.android.untils.Constants.DELIVERY_FEE
import com.example.coffeeshop.android.untils.Constants.DELIVER_TAB_TITLE
import com.example.coffeeshop.android.untils.Constants.ORDER_BTN_TITLE
import com.example.coffeeshop.android.untils.Constants.PAYMENT_SUMMARY_SUBTITLE_DELIVERY_FEE
import com.example.coffeeshop.android.untils.Constants.PAYMENT_SUMMARY_SUBTITLE_PRICE
import com.example.coffeeshop.android.untils.Constants.PAYMENT_SUMMARY_SUBTITLE_TOTAL_PAYMENT
import com.example.coffeeshop.android.untils.Constants.PAYMENT_SUMMARY_TITLE
import com.example.coffeeshop.android.untils.Constants.PICK_UP_TAB_TITLE
import com.example.coffeeshop.android.untils.Constants.SELECTED_PICK_UP_ADDRESS
import com.example.coffeeshop.android.untils.Constants.SELECT_ADDRESS_BTN_TITLE
import com.example.coffeeshop.android.untils.Constants.SELECT_ADDRESS_TITLE
import com.example.coffeeshop.android.untils.Constants.YOUR_ORDER_TITLE

@Composable
fun CartScreen(
    navController: NavController,
    viewModel:CartViewModel
) {

    val cartUIState by viewModel.cartUIState.collectAsState()
    val addressUIState by viewModel.addressUIState.collectAsState()
    val receiveOrderUIState by viewModel.receiveOrderModeUIState.collectAsState()
    val selectedLocationUIState by viewModel.selectedLocationUIState.collectAsState()
    
    val scrollState = rememberScrollState()



    
    if(addressUIState.showingMap.not()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.secondBackground)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = YOUR_ORDER_TITLE,
                fontSize = 24.sp,
                color = AppTheme.colors.secondPrimaryTitle,
                fontFamily = sora,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 25.dp, start = 20.dp)
            )

            Spacer(modifier = Modifier.height(25.dp))

            if(!cartUIState.isLoading && cartUIState.coffee.isEmpty()){
                Box(modifier = Modifier.padding(top = 15.dp)) {
                    Text(
                        text = Constants.DONT_HAVE_ANY_ORDERS,
                        fontSize = 22.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }else if(cartUIState.coffee.isNotEmpty()){
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    TabButtons(
                        currentTitle = receiveOrderUIState,
                        titles = listOf(DELIVER_TAB_TITLE, PICK_UP_TAB_TITLE),
                        modifier = Modifier.fillMaxWidth()
                    ){
                        viewModel.onReceiveModeChange(it)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = DELIVERY_ADDRESS_TITLE,
                        fontSize = 18.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold
                    )
                    if(addressUIState.selectedAddress.isNotEmpty()){
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "",
                                tint = AppTheme.colors.subtitle,
                                modifier = Modifier
                                    .size(18.dp)
                                    .clickable {
                                        viewModel.closeMap()
                                    }
                                )
                            Text(
                                text = addressUIState.selectedAddress,
                                fontSize = 14.sp,
                                color = AppTheme.colors.subtitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if(receiveOrderUIState == DELIVER_TAB_TITLE) {
                        SmallButton(
                            icon = R.drawable.baseline_edit_note_24,
                            title = ADD_ADDRESS_TITLE_BTN
                        ) {
                            viewModel.changeMapActive(true)
                        }
                    }else{
                        if(selectedLocationUIState != null){
                            Text(
                                text = SELECTED_PICK_UP_ADDRESS + selectedLocationUIState!!,
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.Normal
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(14.dp))
                        ){
                            MapView(locations = viewModel.locations){
                                viewModel.onLocationChange(it)
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for(index in 0..<cartUIState.coffee.size){
                            Column(modifier = Modifier.padding(top = 20.dp)) {
                                CartItem(
                                    coffee = cartUIState.coffee[index].first,
                                    amount = cartUIState.coffee[index].second,
                                    modifier = Modifier.fillMaxWidth(),
                                    onPlusClick = {
                                        viewModel.changeAmount(index, 1)
                                    },
                                    onMinusClick = {
                                        viewModel.changeAmount(index, -1)
                                    }
                                )
                                Spacer(modifier = Modifier.height(13.dp))
                                if(index != cartUIState.coffee.size - 1) {
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(AppTheme.colors.borderColor.copy(alpha = 0.7f))
                                    )
                                }
                            }
                        }
                    }
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(AppTheme.colors.bottomListLine))
                    Spacer(modifier = Modifier.height(33.dp))
                    Text(
                        text = PAYMENT_SUMMARY_TITLE,
                        fontSize = 14.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = PAYMENT_SUMMARY_SUBTITLE_PRICE,
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "$${String.format("%.2f", cartUIState.totalPrice)}",
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = PAYMENT_SUMMARY_SUBTITLE_DELIVERY_FEE,
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "$$DELIVERY_FEE",
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = PAYMENT_SUMMARY_SUBTITLE_TOTAL_PAYMENT,
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                text = "$${String.format("%.2f", cartUIState.totalPrice + DELIVERY_FEE)}",
                                fontSize = 14.sp,
                                color = AppTheme.colors.secondPrimaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        AppPrimaryButton(
                            title = ORDER_BTN_TITLE,
                            modifier = Modifier.fillMaxWidth()
                        ){

                        }
                        Spacer(modifier = Modifier.height(70.dp))
                    }
                }
            }

        }
    }else{
        Box(modifier = Modifier.fillMaxSize()) {
            MapView(
                onMapChange = {pair ->
                    viewModel.onAddressChange(pair)
                },
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppTheme.colors.secondBackground)
                    .padding(vertical = 16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = SELECT_ADDRESS_TITLE,
                        fontSize = 18.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    if(addressUIState.selectedAddress.isNotEmpty() && addressUIState.isLoading.not()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = addressUIState.selectedAddress,
                            fontSize = 16.sp,
                            color = AppTheme.colors.secondPrimaryTitle,
                            fontFamily = sora,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(horizontal = 15.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                    if(addressUIState.isLoading){
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
                Box(
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .size(23.dp)
                            .clickable {
                                viewModel.closeMap()
                            },
                        tint = AppTheme.colors.secondPrimaryTitle
                    )
                }

            }
            if(addressUIState.selectedAddress.isNotEmpty()){
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 15.dp)
                        .padding(bottom = 15.dp)
                ) {
                    AppPrimaryButton(
                        title = SELECT_ADDRESS_BTN_TITLE,
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        viewModel.changeMapActive(false)
                    }
                }
            }
        }
    }

}