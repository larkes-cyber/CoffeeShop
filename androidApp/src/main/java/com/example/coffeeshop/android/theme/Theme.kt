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
    val secondSubBackground:Color,
    val thirdSubBackground:Color,
    val unSelectedBottomIcon:Color,
    val firstGradientBackground:Color,
    val secondGradientBackground:Color,
    val thirdGradientBackground:Color,
    val secondSubtitle:Color,
    val thirdPrimaryTitle:Color,
    val fourthPrimaryTitle:Color,
    val fifthPrimaryTitle:Color,
    val strokeColor:Color,
    val smallBtnColor:Color,
    val fourthSubBackground: Color,
    val borderColor:Color,
    val bottomListLine:Color
    )

private val DarkColorPalette = Colors(
    background = Color(0xFF000000),
    secondBackground = Color(0xFFF9F9F9),
    primaryTitle = Color(0xFFFFFFFF),
    secondPrimaryTitle = Color(0xFF2F2D2C),
    primarySubtitle = Color(0xFF2F2D2C),
    subtitle = Color(0xFFA9A9A9),
    subBackground = Color(0xFFF9F9F9),
    secondSubBackground = Color(0xFF000000),
    textField = Color(0xFFE6E0E9),
    thirdBackground = Color(0xFFC67C4E),
    thirdSubBackground = Color(0xFFFFFFFF),
    unSelectedBottomIcon = Color(0xFF8D8D8D),
    firstGradientBackground = Color(0xFF131313),
    secondGradientBackground = Color(0xFF313131),
    thirdGradientBackground = Color(0xFF989898),
    secondSubtitle = Color(0xFFB7B7B7),
    thirdPrimaryTitle = Color(0xFFDDDDDD),
    fourthPrimaryTitle = Color(0xFF2F4B4E),
    fifthPrimaryTitle = Color(0xFF9B9B9B),
    strokeColor = Color(0xFFEAEAEA),
    smallBtnColor = Color(0xFFFFF5EE),
    fourthSubBackground = Color(0xFFF2F2F2),
    borderColor = Color(0xFFDEDEDE),
    bottomListLine = Color(0xFFF4F4F4)

)

private val LightColorPalette = Colors(
    background = Color(0xFF000000),
    secondBackground = Color(0xFFF9F9F9),
    primaryTitle = Color(0xFFFFFFFF),
    secondPrimaryTitle = Color(0xFF2F2D2C),
    primarySubtitle = Color(0xFF2F2D2C),
    subtitle = Color(0xFFA9A9A9),
    subBackground = Color(0xFFF9F9F9),
    secondSubBackground = Color(0xFF000000),
    textField = Color(0xFFE6E0E9),
    thirdBackground = Color(0xFFC67C4E),
    thirdSubBackground = Color(0xFFFFFFFF),
    unSelectedBottomIcon = Color(0xFF8D8D8D),
    firstGradientBackground = Color(0xFF131313),
    secondGradientBackground = Color(0xFF313131),
    thirdGradientBackground = Color(0xFF989898),
    secondSubtitle = Color(0xFFB7B7B7),
    thirdPrimaryTitle = Color(0xFFDDDDDD),
    fourthPrimaryTitle = Color(0xFF2F4B4E),
    fifthPrimaryTitle = Color(0xFF9B9B9B),
    strokeColor = Color(0xFFEAEAEA),
    smallBtnColor = Color(0xFFFFF5EE),
    fourthSubBackground = Color(0xFFF2F2F2),
    borderColor = Color(0xFFDEDEDE),
    bottomListLine = Color(0xFFF4F4F4)
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