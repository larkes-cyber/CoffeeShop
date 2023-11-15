package com.example.coffeeshop.android.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coffeeshop.android.theme.AppTheme

@Composable
fun BottomNavBar(
    items:List<BottomNavItem>,
    navController: NavController,
    onClick: (BottomNavItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()



    val showBottomNavState = remember {
        mutableStateOf(false)
    }

    if(backStackEntry.value?.destination?.route != null){
        val backStackEntryValue = backStackEntry.value?.destination?.route!!.split("/")[0]

        showBottomNavState.value = !((backStackEntryValue == Screen.LoginScreen.route)
                || (backStackEntryValue == Screen.SplashScreen.route)
                || (backStackEntryValue == Screen.StartScreen.route)
                || (backStackEntryValue == Screen.CoffeeDetailScreen.route)
                )
    }
    if(showBottomNavState.value){
        Card(
            modifier = Modifier
                .fillMaxWidth().height(72.dp),
            elevation = 4.dp,
            backgroundColor = AppTheme.colors.thirdSubBackground,
            shape =  RoundedCornerShape(topStart = 24.dp, topEnd = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(72.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items.forEach { item ->
                    if(backStackEntry.value?.destination?.route != null) {
                        val backStack =
                            backStackEntry.value?.destination?.route!!.split("/")[0]
                        val item1 = item.route.split("/")[0]
                        val selected = item1 == backStack
                        BottomNavigationItem(
                            selected = selected,
                            onClick = { onClick(item) },
                            selectedContentColor = AppTheme.colors.thirdBackground,
                            unselectedContentColor = AppTheme.colors.unSelectedBottomIcon,
                            icon = {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(painterResource(id = item.icon), contentDescription = "")
                                }
                            }
                        )
                    }
                }
            }
        }
    }



}