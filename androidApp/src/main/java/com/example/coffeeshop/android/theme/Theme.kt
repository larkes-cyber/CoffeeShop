package com.example.coffeeshop.android.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class Colors(
    val background:Color,
    val secondBackground:Color,
    val primaryTitle:Color,
    val secondPrimaryTitle:Color,
    val primarySubtitle:Color,
    val subtitle:Color,
    val subBackground:Color,
    val thirdBackground:Color,
    val textField:Color,
    val secondSubBackground:Color
)

private val DarkColorPalette = Colors(
    background = Color(0xFF000000),
    secondBackground = Color(0xFFFFFFFF),
    primaryTitle = Color(0xFFFFFFFF),
    secondPrimaryTitle = Color(0xFF2F2D2C),
    primarySubtitle = Color(0xFF2F2D2C),
    subtitle = Color(0xFFA9A9A9),
    subBackground = Color(0xFFF9F9F9),
    secondSubBackground = Color(0xFF000000),
    textField = Color(0xFFE6E0E9),
    thirdBackground = Color(0xFFC67C4E)

)

private val LightColorPalette = Colors(
    background = Color(0xFF000000),
    secondBackground = Color(0xFFFFFFFF),
    primaryTitle = Color(0xFFFFFFFF),
    secondPrimaryTitle = Color(0xFF2F2D2C),
    primarySubtitle = Color(0xFF2F2D2C),
    subtitle = Color(0xFFA9A9A9),
    subBackground = Color(0xFFF9F9F9),
    secondSubBackground = Color(0xFF000000),
    textField = Color(0xFFE6E0E9),
    thirdBackground = Color(0xFFC67C4E)
)

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("fddfdd")
}

@Composable
fun CoffeeShopAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalColorProvider provides LightColorPalette
    ) {
        content()
    }
}